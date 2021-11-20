package com.liu.forfunboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto implements Serializable {

    private String fileName;
    private String filePureName;
    private String fileSuffix;
    private String filePath;

    private boolean isFile;

    private String pathCurrent;

}
