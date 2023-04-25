package com.kodilla.library.domain.copy;

import com.kodilla.library.domain.title.TitleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CopyDto {
    private Long id;
    private TitleDto title;
    private CopyStatus status;
}
