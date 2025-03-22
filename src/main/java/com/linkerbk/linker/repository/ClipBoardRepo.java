package com.linkerbk.linker.repository;

import com.linkerbk.linker.entity.Clipboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClipBoardRepo extends JpaRepository<Clipboard, Long > {
}
