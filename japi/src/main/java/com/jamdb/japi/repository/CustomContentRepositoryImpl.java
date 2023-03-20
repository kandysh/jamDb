package com.jamdb.japi.repository;

import com.jamdb.japi.entities.content.Content;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
@Repository
public class CustomContentRepositoryImpl implements CustomContentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Content> findContentBasedOnQuery(String search, String tag, String year, String season, String status, String type) {
        StringBuilder baseQuery = new StringBuilder("select * from anime where ");
        boolean orderByScoreAndNullsLast = true;
        if (Objects.nonNull(search)) {
            orderByScoreAndNullsLast = false;
            baseQuery.append(
                            "to_tsvector(array_to_string(synonyms,' ') || ' ' || title || ' ' || array_to_string(tags, ' ') ) @@ plainto_tsquery('")
                    .append(search)
                    .append("')");
        }
        if (Objects.nonNull(tag)) {
            orderByScoreAndNullsLast = false;
            if (Objects.nonNull(search))
                baseQuery.append(" and ");
            baseQuery.append("to_tsvector(array_to_string(tags, ' ')) @@ plainto_tsquery('").append(tag).append("') ");
        }
        if (Objects.nonNull(year)) {
            if (Objects.nonNull(tag) || Objects.nonNull(search))
                baseQuery.append(" and ");
            baseQuery.append("year=").append(year);
        }
        if (Objects.nonNull(season)) {
            if (Objects.nonNull(year) || Objects.nonNull(tag) || Objects.nonNull(search))
                baseQuery.append(" and ");
            baseQuery.append(" season='").append(season.toUpperCase()).append("' ");
        }
        if (Objects.nonNull(status)) {
            if (Objects.nonNull(season) || Objects.nonNull(year) || Objects.nonNull(tag) || Objects.nonNull(search))
                baseQuery.append(" and ");
            baseQuery.append(" status='").append(status.toUpperCase()).append("'");
        }
        if (Objects.nonNull(type)) {
            if (Objects.nonNull(status) || Objects.nonNull(season) || Objects.nonNull(year) || Objects.nonNull(
                    tag) || Objects.nonNull(search))
                baseQuery.append(" and ");
            baseQuery.append(" type='").append(type.toUpperCase()).append("' ");
        }
        if (orderByScoreAndNullsLast) {
            baseQuery.append(" order by score desc nulls last ");
        }
        baseQuery.append(" limit 100;");

        return entityManager.createNativeQuery(baseQuery.toString(), Content.class).getResultList();
    }
}
