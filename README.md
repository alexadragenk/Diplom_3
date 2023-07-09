# Diplom_3
## Технологии, используемые в проекте
* Java 11
* JUnit 4.13.2
* maven 3.9.0
* rest-assured 5.2.0
* allure 2.22.1
* allure-maven 2.10.0
* maven-surefire-plugin 2.22.2
* gson 2.8.9
* selenium-java 4.8.1

## Запуск тестов
Тесты запускаются в в двух браузерах: Google Chrome и Яндекс.Браузер.  
Реализовано через параметр в файле config.properties  
Для запуска в Яндекс.Браузере необходимо прописать в config.properties use_yandex_browser = true и указать там же в web_driver_path путь к драйверу  
Запуск тестов:
```
mvn clean test
```
Генерация html отчета:
```
mvn allure:report
```
Просмотр отчета:
```
mvn allure:serve
```
## Описание
Selenium тесты веб-приложения Stellar Burgers.  
Allure отчет добавлен в репозиторий.
