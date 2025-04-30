# 🔐 Keycloak (OAuth2 + Jwt + 2FA + IdP) Test Project

Этот проект демонстрирует интеграцию Spring Boot приложения с системой авторизации **Keycloak** по протоколу **OAuth2 (
Authorization Code Flow)**.  
Для удобства тестирования и просмотра API используется **Swagger UI**, который уже настроен на работу с Keycloak.

#### Также реализованы следующие функции:

- 🔑 Двухфакторная аутентификация (2FA) для новых локальных пользователей с использованием одноразовых паролей (OTP).
- 🌐 Вход через внешний провайдер авторизации Google. Для активации требуется указать значения переменных окружения GOOGLE_CLIENT_ID и GOOGLE_CLIENT_SECRET при запуске docker-compose up.


## 🚀 Быстрый старт

```bash
git clone git@github.com:Pruglo92/keycloak-test.git
```
```bash
cd keycloak-test
```
```bash
docker-compose up
```
или если нужен внешний провайдер Google
```bash
GOOGLE_CLIENT_ID=ваш_id GOOGLE_CLIENT_SECRET=ваш_secret docker-compose up
```

## 🧪 Swagger UI

Swagger доступен по адресу:  
🔗 [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

Для авторизации нажмите кнопку **Authorize** и выполните вход через Keycloak.

username: 
```credentials
test-user
```
password:
```credentials
test-password
```

## ⚠️ Авторизация

Все запросы, кроме Swagger и OpenAPI (`/swagger-ui/**`, `/v3/api-docs/**` и пр.), требуют авторизации через Keycloak.  
При попытке обращения к защищённым эндпоинтам без токена сервер вернёт **401 Unauthorized**.
