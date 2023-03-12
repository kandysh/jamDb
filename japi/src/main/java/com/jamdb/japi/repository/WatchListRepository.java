package com.jamdb.japi.repository;

import com.jamdb.japi.entities.watchList.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WatchListRepository extends JpaRepository<WatchList, UUID> {
}
