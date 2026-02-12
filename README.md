# Firefly Framework - Cache

[![CI](https://github.com/fireflyframework/fireflyframework-cache/actions/workflows/ci.yml/badge.svg)](https://github.com/fireflyframework/fireflyframework-cache/actions/workflows/ci.yml)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-21%2B-orange.svg)](https://openjdk.org)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)](https://spring.io/projects/spring-boot)

> Unified multi-provider caching library with Caffeine, Redis, Hazelcast, and JCache support for Spring Boot applications.

---

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Quick Start](#quick-start)
- [Configuration](#configuration)
- [Documentation](#documentation)
- [Contributing](#contributing)
- [License](#license)

## Overview

Firefly Framework Cache provides a standardized caching abstraction that supports multiple cache providers through a unified API. It features Spring Boot auto-configuration for seamless integration, custom annotations for declarative caching, and a smart cache adapter that can automatically select the optimal provider.

The library implements a provider-agnostic architecture with SPI-based provider discovery, supporting Caffeine for local caching, Redis for distributed caching, Hazelcast for clustered caching, and JCache (JSR-107) for standards compliance. It includes health indicators, serialization support, and comprehensive cache statistics.

The `FireflyCacheManager` orchestrates cache operations across providers, while `CacheSelectionStrategy` allows automatic or manual provider selection based on cache characteristics.

## Features

- Multi-provider support: Caffeine, Redis, Hazelcast, JCache
- Custom annotations: `@Cacheable`, `@CachePut`, `@CacheEvict`, `@Caching`, `@EnableCaching`
- Smart cache adapter with automatic provider selection
- SPI-based provider discovery and factory pattern
- JSON cache serialization with pluggable serializers
- Cache health indicators for Spring Boot Actuator
- Cache statistics and metrics collection
- Spring Boot auto-configuration (`CacheAutoConfiguration`, `RedisCacheAutoConfiguration`)
- Configurable via `CacheProperties` with application.yml support

## Requirements

- Java 21+
- Spring Boot 3.x
- Maven 3.9+
- Redis (optional, for distributed caching)

## Installation

```xml
<dependency>
    <groupId>org.fireflyframework</groupId>
    <artifactId>fireflyframework-cache</artifactId>
    <version>26.02.02</version>
</dependency>
```

## Quick Start

```java
import org.fireflyframework.cache.annotation.Cacheable;
import org.fireflyframework.cache.annotation.CacheEvict;

@Service
public class ProductService {

    @Cacheable(cacheName = "products", key = "#id")
    public Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }

    @CacheEvict(cacheName = "products", key = "#id")
    public Mono<Void> evict(String id) {
        return Mono.empty();
    }
}
```

## Configuration

```yaml
firefly:
  cache:
    provider: caffeine  # caffeine, redis, hazelcast, jcache
    caffeine:
      maximum-size: 10000
      expire-after-write: 5m
    redis:
      host: localhost
      port: 6379
      ttl: 10m
```

## Documentation

Additional documentation is available in the [docs/](docs/) directory:

- [Quickstart](docs/QUICKSTART.md)
- [Architecture](docs/ARCHITECTURE.md)
- [Configuration](docs/CONFIGURATION.md)
- [Api Reference](docs/API_REFERENCE.md)
- [Auto Configuration](docs/AUTO_CONFIGURATION.md)
- [Examples](docs/EXAMPLES.md)
- [Monitoring](docs/MONITORING.md)
- [Optional Dependencies](docs/OPTIONAL_DEPENDENCIES.md)
- [Testing](docs/TESTING.md)

## Contributing

Contributions are welcome. Please read the [CONTRIBUTING.md](CONTRIBUTING.md) guide for details on our code of conduct, development process, and how to submit pull requests.

## License

Copyright 2024-2026 Firefly Software Solutions Inc.

Licensed under the Apache License, Version 2.0. See [LICENSE](LICENSE) for details.
