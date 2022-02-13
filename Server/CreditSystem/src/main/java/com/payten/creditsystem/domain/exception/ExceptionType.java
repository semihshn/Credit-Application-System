package com.payten.creditsystem.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {

    GENERIC_EXCEPTION(1, "Bilinmeyen bir sorun oluştu"),

    USER_DATA_NOT_FOUND(1001, "Kullanıcı bulunamadı"),
    COMMUNICATION_DATA_NOT_FOUND(1002, "İletişim bilgileri bulunamadı"),
    CREDIT_APPLICATION_INFORMATION_DATA_NOT_FOUND(1003, "Kredi kabul bilgileri bulunamadı"),
    MAIL_NOT_FOUND(1004, "Mail adresi bulunamadı"),
    ACCOUNT_NOT_FOUND(1005, "Hesap bulunamadı"),

    COLLECTION_SIZE_EXCEPTION(2001, "Liste boyutları uyuşmuyor"),
    MAIL_EXISTS(2002, "Bu mail adresi kullanılmaktadır"),
    IDENTIFICATION_NUMBER_EXISTS(2003, "Bu kimlik numarası kullanılmaktadır"),

    CREDIT_SCORE_INSUFFICIENT(3001,"Kredi skorunuz yetersiz"),

    AUTHENTICATION_ERROR(4001, "Yetkiniz yok");

    private final Integer code;
    private final String message;
}
