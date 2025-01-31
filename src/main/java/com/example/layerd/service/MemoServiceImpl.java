package com.example.layerd.service;

import com.example.layerd.dto.MemoRequestDto;
import com.example.layerd.dto.MemoResponseDto;
import com.example.layerd.entity.Memo;
import com.example.layerd.repository.MemoRepository;
import org.springframework.stereotype.Service;

@Service
public class MemoServiceImpl implements MemoService{

    private final MemoRepository respository;

    public MemoServiceImpl(MemoRepository respository) {
        this.respository = respository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto dto) {
        //요청받은 데이터로 메모 객체 생성(요청으로 온 데이터에는 식별자 값이 없음)
        //그래서 ID 값이 없는 메모 객체 생성
        Memo memo = new Memo(dto.getTitle(), dto.getContents());

        //DB에 저장
        //Repository layer를 호출
        Memo savedMemo = respository.saveMemo(memo);

        return new MemoResponseDto(savedMemo);

    }
}
