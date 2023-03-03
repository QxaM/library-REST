package com.kodilla.library.mapper;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyDto;
import org.springframework.stereotype.Service;

@Service
public class CopyMapper {

    public Copy mapToCopy(final CopyDto copyDto) {
        return new Copy(
                copyDto.getId(),
                copyDto.getTitle(),
                copyDto.getStatus(),
                copyDto.getRentsList());
    }

    public CopyDto mapToCopyDto(final Copy copy) {
        return new CopyDto(
                copy.getId(),
                copy.getTitle(),
                copy.getStatus(),
                copy.getRentsList());
    }
}
