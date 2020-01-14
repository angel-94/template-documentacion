## Ejemplo de documentación con AsciiDoc

- Documentación con ASCIIDOCTOR

### Referencias de plugins usados
- [grgit](https://github.com/ajoberstar/grgit)
- [gradle-git-publish](https://github.com/ajoberstar/gradle-git-publish)

### Configuraciones previas

Crear las siguientes variables de ambiente
```
GRGIT_USER=somebody
GRGIT_PASS=myauthtoken
```

### Proceso de construcción

- Ejecutar la siguiente tarea de Gradle

```
./gradlew asciidoctor
```

- Posteriormente ejecutar la siguiente tarea para la publicación de nuestra documentación
```
gradle gitPublishPush
```
