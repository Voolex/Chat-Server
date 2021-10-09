# Chat-Server
Серверная часть чата

#### Стек технологий
* Java
* Spring Boot
* MySQL
* Hibernate

#### Описание возможностей
* Личные сообщения
* Групповые чаты
* Секретные личные чаты с шифрованием end-to-end


#### Доступные роли
* ROLE_USER - простой user
* ROLE_ADMIN - роль администратора. Функционал пока не придуман.


## Архитектура
### Общая схема взаимодействия клиент-сервер
Пользователь отправляет сообщение другому пользователю. Сообщение попадает на сервер. С сервера пользователю отправляется уведомление о новом сообщении.
Предварительная структура:


### Таблицы в БД
#### USERS
Содержит базовую информацию о пользователе
| Наименование поля | Тип данных |
|:------------------|:----------:|
|userid             |number      |
|username           |varchar     |
|password           |varchar     |
|is_banned          |bool        |
