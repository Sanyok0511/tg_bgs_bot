package com.elite.dangerous.db.repository;

import com.elite.dangerous.db.entity.TgMessage;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.CrudRepository;

@Lazy
public interface TgMessageRepository extends CrudRepository<TgMessage, Long> {

}
