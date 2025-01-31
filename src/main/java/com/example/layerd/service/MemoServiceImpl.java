package com.example.layerd.service;

import com.example.layerd.dto.MemoRequestDto;
import com.example.layerd.dto.MemoResponseDto;
import com.example.layerd.entity.Memo;
import com.example.layerd.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @Override
    public List<MemoResponseDto> findAllMemos() {
        List<MemoResponseDto> allMemos = respository.findAllMemos();

        return allMemos;
    }

    @Override
    public MemoResponseDto findMemoById(Long id) {
        Memo memoById = respository.findMemoById(id);

        if (memoById == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id가 존재하지 않습니다.");
        }

        return new MemoResponseDto(memoById);
    }

    @Override
    public MemoResponseDto updateMemo(Long id, String title, String contents) {
        Memo memoById = respository.findMemoById(id);

        if (memoById == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id가 존재하지 않습니다.");
        }

        if (title == null || contents == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title과 contents 모두 입력되어야 합니다.");
        }

        memoById.update(title, contents);

        return new MemoResponseDto(memoById);

    }

    @Override
    public MemoResponseDto updateTitle(Long id, String title, String contents) {
        Memo memoById = respository.findMemoById(id);

        if (memoById == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id가 존재하지 않습니다.");
        }

        if (title == null || contents != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "title만 필수로 입력되어야 합니다.");
        }

        memoById.updateTitle(title);

        return new MemoResponseDto(memoById);
    }

    @Override
    public void deleteMemo(Long id) {
        Memo memoById = respository.findMemoById(id);

        if (memoById == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id가 존재하지 않습니다.");
        }

        respository.deleteMemo(id);

    }
}
