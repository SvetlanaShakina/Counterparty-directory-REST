# Counterparty-directory-REST

Справочник контрагентов

Функционал: добавление, редактирование, удаление контрагентов. Поиск по наименованию или БИК и номеру счета.

Сборка проекта: $ mvn package

Запуск проекта: $ java -jar target\counterparty-directory-rest-0.0.1-SNAPSHOT.jar

Скрипт для развертывания БД лежит в папке sql/counterparty_db.sql. В файле application.properties перед запуском необходимо прописать ссылку для подключения к базе данных.