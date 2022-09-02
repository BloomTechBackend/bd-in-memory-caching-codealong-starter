package com.amazon.ata.inmemorycaching.classroom.dao;

import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupMembershipCacheKey;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

// TODO 1
//          Remind yourself of what was needed to build the caching dao for the Guided Project.
//          How will you create a caching dao for the GroupDao?
//          Consider what is different between these two situations.
public class GroupMembershipCachingDao {

    private LoadingCache<GroupMembershipCacheKey, Boolean> cache;

    @Inject
    public GroupMembershipCachingDao(final GroupMembershipDao delegateDao) {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(20000)
                .expireAfterWrite(3, TimeUnit.HOURS)
                .build(CacheLoader.from(delegateDao::isUserInGroup));
    }

    public boolean isUserInGroup(final String userId, final String groupId) {
        return cache.getUnchecked(new GroupMembershipCacheKey(userId, groupId));
    }
}
