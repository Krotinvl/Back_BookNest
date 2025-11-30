## Back_BookNest

Краткое руководство по проекту BookNest.

## Technologies
- Java 21
- Spring Boot 3.5.7
- PostgreSQL
- Gradle
- JPA / Hibernate

## Quick Start
 Клонируйте репозиторий:

```bash
git clone https://github.com/Krotinvl/Back_BookNest.git
```


## API Endpoints 
Ниже endpoints сгруппированы по функционалу с кратким описанием.

### Authentication
- `POST /api/auth/register` — регистрация нового пользователя.

	Пример Body (JSON):
	```json
	{
		"username": "",
		"email": "",
		"password": "",
		"name": ""
	}
	```

### Books
- `GET /api/books` — список всех книг.
- `GET /api/books/{id}` — информация о книге по ID.
- `GET /api/books/search?query=...` — поиск книг по запросу.
- `GET /api/books/author/{author}` — список книг по автору.
- `GET /api/books/genre/{genre}` — список книг по жанру.
- `POST /api/books` — создать новую книгу.

	Пример Body (JSON):
	```json
	{
		"title": "",
		"author": "",
		"genre": "",
		"pages": 320,
		"description": "",
		"publishing": "",
		"dateOfPublication": "2020-01-01",
		"cycle": "",
		"image": "base64",
		"imageType": ""
	}
	```

### Reviews
- `GET /api/books/{id}/review` — получить отзывы для книги.

### User profile
- `GET /api/users/{username}` — получить профиль пользователя.
- `PUT /api/users/{username}` — обновить профиль пользователя.

	Пример Body (JSON):
	```json
	{
		"name": "",
		"email": "",
		"description": "",
		"telephone": ""
	}
	```

### Library
- `GET /api/users/{username}/library` — получить все записи библиотеки пользователя.
- `GET /api/users/{username}/library/{collection}` — получить записи по коллекции.
- `POST /api/users/{username}/library` — добавить книгу в библиотеку.

	Пример Body (JSON):
	```json
	{
		"bookId": 
	}
	```
 - `PUT /api/users/{username}/library/{bookId}/collection` — изменить коллекцию у записи.

	Пример Body (JSON):
	```json
	{
		"collection": ""
	}
	```

### Quotes
- `GET /api/users/{username}/quotes` — получить все цитаты пользователя.
- `GET /api/users/quotes/{quoteId}` — получить цитату по ID.

### Characters
- `GET /api/users/{username}/characters` — получить список персонажей пользователя.
