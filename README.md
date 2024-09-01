# Микросервис для хранения файлов

## Описание решения

Этот микросервис реализует хранилище для различных файлов и их атрибутов, таких как название, дата создания и краткое описание. Файлы передаются в формате Base64 и сохраняются в базе данных PostgreSQL вместе с метаданными. Микросервис предоставляет API для загрузки и получения файлов, а также для получения списка всех файлов с поддержкой пагинации и сортировки по дате создания.

### Основные API методы:

1. **Создание файла** (`POST /api/files`): принимает файл и его атрибуты и сохраняет их в базе данных.
2. **Получение файла** (`GET /api/files/{id}`): возвращает файл и его атрибуты по ID.
3. **Получение списка файлов** (`GET /api/files`): возвращает список файлов с поддержкой пагинации и сортировки.

## Инструкция по запуску приложения

### 1. Клонирование репозитория

Склонируйте репозиторий на свой локальный компьютер:

```
git clone https://github.com/your-username/microservice-file-storage.git
cd microservice-file-storage
```

### 2. Настройка конфигурации
Убедитесь, что файл src/main/resources/application.properties настроен правильно. Если нужно, измените настройки подключения к базе данных:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/filesdb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Сборка проекта
Соберите проект с помощью Maven:


```
mvn clean package
```
### 4. Запуск с Docker
Запустите проект с использованием Docker Compose:


```
docker-compose up --build
```
Это запустит два контейнера: один с приложением и один с базой данных PostgreSQL.

Приложение будет доступно по адресу http://localhost:8080.

Примеры тестовых запросов для проверки API-методов
### 1. Создание файла
   Запрос: POST /api/files

Тело запроса (JSON):

```
{
"title": "Test File",
"creationDate": "2024-08-27T12:34:56",
"description": "This is a test file",
"fileData": "VGhpcyBpcyBhIHRlc3QgZmlsZQ=="
}
```
Пример ответа (JSON):

```
{
"id": 1
}
```

### 2. Получение файла
Запрос: GET /api/files/1

Пример ответа (JSON):

```
{
"id": 1,
"title": "Test File",
"creationDate": "2024-08-27T12:34:56",
"description": "This is a test file",
"fileData": "VGhpcyBpcyBhIHRlc3QgZmlsZQ=="
}
```

### 3. Получение списка файлов
   Запрос: GET /api/files?page=0&size=10

Пример ответа (JSON):

```
[
{
"id": 1,
"title": "Test File",
"creationDate": "2024-08-27T12:34:56",
"description": "This is a test file",
"fileData": "VGhpcyBpcyBhIHRlc3QgZmlsZQ=="
},
{
"id": 2,
"title": "Another File",
"creationDate": "2024-08-28T09:20:30",
"description": "Another test file",
"fileData": "QW5vdGhlciBmaWxlIGNvbnRlbnQ="
}
]
```

Завершение работы
Чтобы остановить и удалить контейнеры, выполните следующую команду:

```
docker-compose down
```
