package com.jamdb.japi.repository;

import com.jamdb.japi.entities.watchList.WatchList;
import com.jamdb.japi.entities.watchList.WatchListId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WatchListRepository extends JpaRepository<WatchList, WatchListId> {
    List<WatchList> findWatchListById_UserId(UUID uuid);
}
