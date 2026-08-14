# DS & LLD Practice

A standalone Java workspace for data-structures, algorithms, and low-level-design exercises. It has no Spring Boot or application framework dependencies.

## Run

```bash
mvn compile
java -cp target/classes com.practice.ds.DataStructuresPractice
java -cp target/classes com.practice.lld.parkinglot.ParkingLotDemo
```

## Layout

- `src/main/java/com/practice/ds` — data-structure and algorithm exercises.
- `src/main/java/com/practice/lld` — low-level design exercises.

Each topic should have a focused class and, where useful, a small `main` method to experiment with it.
