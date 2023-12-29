package com.jeontongju.notification.mapper;

import com.jeontongju.notification.domain.Notification;
import com.jeontongju.notification.dto.response.NotificationInfoForInquiryResponseDto;
import com.jeontongju.notification.dto.response.NotificationInfoForSingleInquiryDto;
import com.jeontongju.notification.dto.response.NotificationInfoResponseDto;
import io.github.bitbox.bitbox.enums.NotificationTypeEnum;
import io.github.bitbox.bitbox.enums.RecipientTypeEnum;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

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

  public Notification toIncludedRedirectLinkEntity(
      Long recipientId,
      RecipientTypeEnum recipientTypeEnum,
      NotificationTypeEnum notificationType,
      String redirectLink) {

    return Notification.builder()
        .recipientId(recipientId)
        .recipientTypeEnum(recipientTypeEnum)
        .notificationTypeEnum(notificationType)
        .redirectLink(redirectLink)
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

  public NotificationInfoResponseDto toNotificationDto(Long notificationId, Object data) {

    return NotificationInfoResponseDto.builder().notificationId(notificationId).data(data).build();
  }
}
