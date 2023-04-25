package com.kodilla.library.mapper;

import com.kodilla.library.domain.copy.Copy;
import com.kodilla.library.domain.copy.CopyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopyMapper {

    private final TitleMapper titleMapper;

    public Copy mapToCopy(final CopyDto copyDto) {
        return new Copy(
                copyDto.getId(),
                titleMapper.mapToTitle(copyDto.getTitle()),
                copyDto.getStatus(),
                new ArrayList<>());
    }

    public CopyDto mapToCopyDto(final Copy copy) {
        return new CopyDto(
                copy.getId(),
                titleMapper.mapToTitleDto(copy.getTitle()),
                copy.getStatus());
    }

    public List<CopyDto> mapToCopyDtoList(final List<Copy> copies) {
        return copies.stream()
                .map(this::mapToCopyDto)
                .collect(Collectors.toList());
    }
}
