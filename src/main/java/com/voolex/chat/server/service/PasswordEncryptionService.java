package com.voolex.chat.server.service;


/**
 * Сервис, служащий для хэширования и проверки пароля пользователя
 *
 */
public interface PasswordEncryptionService {
    /**
     * Метод хэширует строку
     * @param source исходный пароль (не хэшированный)
     * @return хэш пароля
     */
    public String encode(String source);

    /**
     * Метод сравнивает исходный (сырой) пароль с уже захэшированным паролем
     * @param rawPassword исходный пароль
     * @param encodedPassword хэшированный пароль
     * @return true, если хэш исходного пароля равен уже переданному хэшу во втором параметре, false в противном случае
     */
    public boolean matches(String rawPassword, String encodedPassword);
}
