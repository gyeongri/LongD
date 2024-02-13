package com.longd.longd.fileUpload.db.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UploadResultDto {

    private String pathUrl;
    private LocalDate createDate;

}
