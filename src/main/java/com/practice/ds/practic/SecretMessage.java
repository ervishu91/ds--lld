
package com.practice.ds.practic;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecretMessage {

    public static void printGrid(String url)
            throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException(
                    "Failed to fetch document. HTTP status: "
                            + response.statusCode()
            );
        }

        String html = response.body();

        Map<Integer, Map<Integer, String>> grid =
                new HashMap<>();

        Pattern rowPattern = Pattern.compile(
                "(?is)<tr\\b[^>]*>(.*?)</tr>"
        );

        Pattern cellPattern = Pattern.compile(
                "(?is)<t[dh]\\b[^>]*>(.*?)</t[dh]>"
        );

        Matcher rowMatcher = rowPattern.matcher(html);

        int maxX = 0;
        int maxY = 0;
        int parsedRows = 0;

        while (rowMatcher.find()) {
            String row = rowMatcher.group(1);
            Matcher cellMatcher = cellPattern.matcher(row);

            List<String> cells = new ArrayList<>();

            while (cellMatcher.find()) {
                cells.add(cleanHtml(cellMatcher.group(1)));
            }

            // Skip headers and malformed rows.
            if (cells.size() != 3) {
                continue;
            }

            try {
                int x = Integer.parseInt(cells.get(0));
                String character = cells.get(1);
                int y = Integer.parseInt(cells.get(2));

                grid.computeIfAbsent(y, k -> new HashMap<>())
                        .put(x, character);

                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
                parsedRows++;

            } catch (NumberFormatException ignored) {
                // The header row contains text, not coordinates.
            }
        }

        System.out.println("Parsed data rows: " + parsedRows);
        System.out.println("Grid dimensions: "
                + (maxX + 1) + " x " + (maxY + 1));

        // y increases upward, so print from maxY down to 0.
//        for (int y = maxY; y >= 0; y--) {
//            Map<Integer, String> row = grid.get(y);
//
//            for (int x = 0; x <= maxX; x++) {
//                String character = row == null
//                        ? null
//                        : row.get(x);
//
//                System.out.print(
//                        character == null ? " " : character
//                );
//            }
//
//            System.out.println();
//        }
        for (int y = maxY; y >= 0; y--) {
            Map<Integer, String> row = grid.get(y);

            for (int x = 0; x <= maxX; x++) {
                String ch = row == null ? null : row.get(x);

                if ("█".equals(ch)) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    private static String cleanHtml(String html) {
        String text = html.replaceAll("(?is)<[^>]*>", "");

        text = text.replace("&nbsp;", " ")
                .replace("&#160;", " ")
                .replace("&amp;", "&")
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"")
                .replace("&#39;", "'")
                .replace("&apos;", "'");

        Pattern entityPattern = Pattern.compile(
                "&#(x[0-9a-fA-F]+|[0-9]+);"
        );

        Matcher matcher = entityPattern.matcher(text);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String entity = matcher.group(1);

            int codePoint = entity.startsWith("x")
                    ? Integer.parseInt(entity.substring(1), 16)
                    : Integer.parseInt(entity);

            matcher.appendReplacement(
                    result,
                    Matcher.quoteReplacement(
                            new String(Character.toChars(codePoint))
                    )
            );
        }

        matcher.appendTail(result);

        return result.toString().trim();
    }

    public static void main(String[] args)
            throws IOException, InterruptedException {

        String url =
                "https://docs.google.com/document/d/e/"
                        + "2PACX-1vSvM5gDlNvt7npYHhp_XfsJvuntUhq184By5xO_pA4b_gCWeXb6dM6ZxwN8rE6S4ghUsCj2VKR21oEP/pub";

        printGrid(url);
    }
}
