package com.sheila.com.ecommerce.utils.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sheila.com.ecommerce.utils.enums.ErrorCode;
import com.sheila.com.ecommerce.utils.enums.ResponseType;
import com.sheila.com.ecommerce.utils.enums.WarningCode;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Builder
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {
    private ResponseType responseType;
    private Object generatedId;
    private String generatedCode;
    private Object payLoad;
    private Throwable exception;
    private String exceptionClass;
    private String message;
    private ErrorCode errorCode;
    private WarningCode warningCode;
    private Long messageTracker;

    private CustomResponse(ResponseType responseType,
                           Object generatedId,
                           String generatedCode,
                           Object payLoad,
                           Throwable exception,
                           String exceptionClass,
                           String message,
                           ErrorCode errorCode,
                           WarningCode warningCode,
                           Long messageTracker) {
        this.messageTracker = messageTracker;
        this.generatedId = generatedId;
        this.generatedCode = generatedCode;
        this.payLoad = payLoad;
        this.warningCode = warningCode;
        this.message = message;

       this.responseType= Optional.ofNullable(responseType).orElse(ResponseType.SUCCESS);
    };

}


//    private CustomResponse(ResponseType responseType,
//                           Object generatedId,
//                           String generatedCode,
//                           Object payLoad,
//                           Throwable exception,
//                           String exceptionClass,
//                           String message,
//                           ErrorCode errorCode,
//                           WarningCode warningCode,
//                           Long messageTracker) {
//        this.messageTracker=messageTracker;
//        this.generatedId = generatedId;
//        this.generatedCode = generatedCode;
//        this.payLoad = payLoad;
////        this.exception = exception; //makes the response too heavy
//        this.warningCode = warningCode;
//        this.message = message;
//        //default responseType to SUCCESS if set to null
//        responseType=Optional.ofNullable(responseType).orElse(ResponseType.SUCCESS);
//
//        //Assign relevant ResponseType based on provided error or warning code, else retain it as is
//        this.responseType = errorCode==null?
//                (warningCode==null?responseType:ResponseType.WARNING)
//                :ResponseType.ERROR;
//        //extract message and error/warning types from the exception if it exists
//        if(exception!=null) {
//            this.message = exception.getMessage();
//            this.exceptionClass = exception.getClass().getSimpleName();
//            if(responseType==ResponseType.WARNING)
//                this.warningCode = warningCode==null?WarningCode.UNDEFINED_WARNING:warningCode;
//            else {
//                this.responseType=ResponseType.ERROR;
//                this.errorCode = errorCode==null?ErrorCode.UNDEFINED_ERROR:errorCode;
//            }
//        }
//        else if(responseType==ResponseType.ERROR) {
//            this.exceptionClass = "";
//        }
//    }
//}


//public CustomResponse search(OrganizationsSearchModel model) {
//    var jpaQuery = new JPAQuery<OrganizationsEntity>(entityManager);
//    var builder = new BooleanBuilder();
//
//
//    Optional.ofNullable(model.getId()).ifPresent(
//            val -> builder.and(organizationsEntity.id.eq(val))
//    );
//    Optional.ofNullable(model.getOrgTypes())
//            .filter(types -> !types.isEmpty())
//            .ifPresent(orgTypes -> builder.and(organizationsEntity.orgTypes.any().in(orgTypes)));
//    Optional.ofNullable(model.getOrgCode()).ifPresent(
//            val -> builder.and(organizationsEntity.orgCode.eq(val))
//    );
//    Optional.ofNullable(model.getCountryCode()).ifPresent(
//            val -> builder.and(organizationsEntity.countryCode.eq(val))
//    );
//    Optional.ofNullable(model.getParentOrgId()).ifPresent(
//            val -> builder.and(organizationsEntity.parentOrg.id.eq(val))
//    );
//    Optional.ofNullable(model.getEmail()).ifPresent(
//            val -> builder.and(organizationsEntity.emails.contains(val))
//    );
//    Optional.ofNullable(model.getPhone()).ifPresent(
//            val -> builder.and(organizationsEntity.phones.contains(val))
//    );
//    Optional.ofNullable(model.getStatus()).ifPresent(
//            val -> builder.and(organizationsEntity.status.eq(val))
//    );
//    Optional.ofNullable(model.getIsDefault())
//            .ifPresentOrElse(
//                    val -> builder.and(organizationsEntity.isDefault.eq(val)),
//                    () -> builder.and(organizationsEntity.isDefault.eq(0))
//            );
//
//    jpaQuery.from(organizationsEntity)
//            .where(builder);
//    var payload = jpaQuery.select(organizationsEntity).fetch().stream()
//            .map(organization -> {
//                var organizationDTO = modelMapper.map(organization, OrganizationsDTO.class);
//                organizationDTO.setLogoUrl(this.getLogoUrl(organization));
//                return organizationDTO;
//            }).collect(Collectors.toList());
//
//    return CustomResponse.builder()
//            .payLoad(payload)
//            .build();
//}

