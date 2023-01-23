package com.mykolas.ignitismessagetask.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findAll();

    Integer countAllByAuthorId(Long id);

    @Query(value = "SELECT MIN(time) FROM messages WHERE author_id = ?1", nativeQuery = true)
    LocalDateTime findByEarliestMeesageTime(Long userId);

    @Query(value = "SELECT MAX(time) FROM messages WHERE author_id = ?1", nativeQuery = true)
    LocalDateTime findLastMessageDate(Long userId);

    @Query(value = "SELECT AVG(length) FROM messages WHERE author_id = ?1", nativeQuery = true)
    Double findAverageMessageLength(Long userId);

    @Query(value = "SELECT content FROM messages WHERE author_id = ?1 AND time = ?2", nativeQuery = true)
    String findMessageContentOfLatestMessage(Long userId, LocalDateTime timeLatestMessage);
}
