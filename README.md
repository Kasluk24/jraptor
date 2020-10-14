# Raptor ![Build Status](https://github.com/raoulvdberge/raptor/workflows/Java%20CI/badge.svg)

Simple Java implementation of the [Raptor (Round-Based Public Transit Routing)](https://www.microsoft.com/en-us/research/wp-content/uploads/2012/01/raptor_alenex.pdf) algorithm.

## Getting started
If you want to use this library you can:
- Use the `InMemoryRaptorFactory` which requires trips and transfers. You can either create those trips and transfers yourself, or you can use `RaptorBuilder` to build them in a declarative way. Keep in mind that `InMemoryRaptorFactory` is slow and uses [in-memory provider implementations](https://github.com/raoulvdberge/raptor/tree/master/src/main/java/com/raoulvdberge/raptor/provider/impl). It should only be used on small data sets and/or for testing purposes.
- ...or: implement the various [provider interfaces](https://github.com/raoulvdberge/raptor/tree/master/src/main/java/com/raoulvdberge/raptor/provider) that the `Raptor` class needs, and pass those to the `Raptor` class. By implementing these interfaces yourself instead of using the [in-memory provider implementations](https://github.com/raoulvdberge/raptor/tree/master/src/main/java/com/raoulvdberge/raptor/provider), you can get better performance.

## Examples
For examples, please check out [the tests](https://github.com/raoulvdberge/raptor/blob/master/src/test/java/com/raoulvdberge/raptor/RaptorTest.java).

## GTFS
Currently, there is no out of the box way to create a `Raptor` instance for a GTFS dataset, but you can build that yourself by implementing the [provider interfaces](https://github.com/raoulvdberge/raptor/tree/master/src/main/java/com/raoulvdberge/raptor/provider).

## Credits
Inspired by [Planar Network's TypeScript implementation](https://github.com/planarnetwork/raptor).
