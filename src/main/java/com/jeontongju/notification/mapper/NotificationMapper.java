package com.jeontongju.notification.mapper;

import com.jeontongju.notification.domain.Notification;

import com.jeontongju.notification.dto.response.NotificationInfoForInquiryResponseDto;
import com.jeontongju.notification.dto.response.NotificationInfoForSingleInquiryDto;
import io.github.bitbox.bitbox.enums.NotificationTypeEnum;
import io.github.bitbox.bitbox.enums.RecipientTypeEnum;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationMapper {

  public Notification toEntity(
      Long recipientId,
      RecipientTypeEnum recipientTypeEnum,
      NotificationTypeEnum notificationTypeEnum) {

    return Notification.builder()
        .recipientId(recipientId)
        .recipientTypeEnum(recipientTypeEnum)
        .notificationTypeEnum(notificationTypeEnum)
        .build();
  }

  public List<NotificationInfoForSingleInquiryDto> toListLookupDto(
      List<Notification> notifications) {

    List<NotificationInfoForSingleInquiryDto> inquiryResponseDtos = new ArrayList<>();
    for (Notification notification : notifications) {

      NotificationInfoForSingleInquiryDto build =
          NotificationInfoForSingleInquiryDto.builder()
              .notificationId(notification.getNotificationId())
              .notificationType(notification.getNotificationTypeEnum())
              .isRead(notification.getIsRead())
              .createdAt(notification.getCreatedAt())
              .build();
      inquiryResponseDtos.add(build);
    }
    return inquiryResponseDtos;
  }

  public NotificationInfoForInquiryResponseDto toInquiryDto(
      int notReadCounts, List<NotificationInfoForSingleInquiryDto> notifications) {

    return NotificationInfoForInquiryResponseDto.builder()
        .notReadcounts(notReadCounts)
        .notifications(notifications)
        .build();
  }
}
