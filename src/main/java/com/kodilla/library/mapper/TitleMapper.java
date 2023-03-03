package com.kodilla.library.mapper;

import com.kodilla.library.domain.title.Title;
import com.kodilla.library.domain.title.TitleDto;
import org.springframework.stereotype.Service;

@Service
public class TitleMapper {

    public Title mapToTitle(final TitleDto titleDto) {
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublicationYear(),
                titleDto.getCopies());
    }

    public TitleDto mapToTitleDto(final Title title) {
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationYear(),
                title.getCopies());
    }
}
