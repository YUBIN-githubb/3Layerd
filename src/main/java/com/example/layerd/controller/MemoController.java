package com.example.layerd.controller;

import com.example.layerd.dto.MemoRequestDto;
import com.example.layerd.dto.MemoResponseDto;
import com.example.layerd.entity.Memo;
import com.example.layerd.repository.MemoRepository;
import com.example.layerd.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //@Controller + @ResponseBody
@RequestMapping("/memos") //url prefix
public class MemoController {

    // 주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지할 수 있다.
    private final MemoService memoService;

    /**
     * 생성자 주입
     * 클래스가 필요로 하는 의존성을 생성자를 통해 전달하는 방식
     * @param memoService @Service로 등록된 MemoService 구현체인 Impl
     */
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    //메모 생성
    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {

        //Service layer를 호출, 받은 응답 반환
        return new ResponseEntity<>(memoService.saveMemo(dto), HttpStatus.CREATED);
    }

    //메모 목록 조회
    @GetMapping
    public ResponseEntity<List<MemoResponseDto>>  findAllMemos() {

        return new ResponseEntity<>(memoService.findAllMemos(), HttpStatus.OK);
    }

    //메모 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {

        return new ResponseEntity<>(memoService.findMemoById(id), HttpStatus.OK);
    }

    //메모 전체 수정
    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto dto) {

        return new ResponseEntity<>(memoService.updateMemo(id, dto.getTitle(), dto.getContents()), HttpStatus.OK);
    }
}
