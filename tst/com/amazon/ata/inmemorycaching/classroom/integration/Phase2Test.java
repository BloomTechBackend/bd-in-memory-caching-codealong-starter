package com.amazon.ata.inmemorycaching.classroom.integration;

import com.amazon.ata.inmemorycaching.classroom.activity.GetGroupActivity;
import com.amazon.ata.inmemorycaching.classroom.dao.models.Group;
import com.amazon.ata.inmemorycaching.classroom.dao.models.GroupType;
import com.amazon.ata.inmemorycaching.classroom.exception.GroupNotFoundException;
import com.amazon.ata.inmemorycaching.classroom.integration.helper.ActivityProvider;
import com.amazon.ata.inmemorycaching.classroom.integration.helper.TestDataProvider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

// TODO 4
//          The last tests below should pass if your CachingDao has been successfully completed.
//          Add break points on each of the handleRequest calls of this last test.
//          Add break points on the first line of each getGroup methods in both the
//          GroupDao and the GroupCachingDao.
//          Debug the final test and use StepOver to trace the Codepath to find which getGroup methods
//          execute. Then, resume the program for the next handleRequest to execute and repeat.
public class Phase2Test {

    private GetGroupActivity getGroupActivity;
    private TestDataProvider testDataProvider;

    @BeforeEach
    private void setup() {
        getGroupActivity = ActivityProvider.provideGetGroupActivity();
        testDataProvider = new TestDataProvider();
    }

    @Test
    public void checkGetGroupActivity_getIdOnAddedGroup_returnsSameGroupId() {
        // GIVEN
        Group group = testDataProvider.createGroup(testDataProvider.generateGroupName(), GroupType.DISCUSSION_GROUP);
        String groupId = group.getId();
        String groupName = group.getName();
        GroupType groupType = group.getType();

        // WHEN
        Group result = getGroupActivity.handleRequest(groupId);

        // THEN
        assertEquals(groupId, group.getId(), "Id should match when just created");
        assertEquals(groupName, group.getName(), "Name should match when just created");
        assertEquals(groupType, group.getType(), "Type should match when just created");
    }


    @Test
    public void checkGetGroupActivity_getIdOnNonGroup_throwsException() {
        // GIVEN
        Group group = testDataProvider.createGroup(testDataProvider.generateGroupName(), GroupType.DISCUSSION_GROUP);
        String groupId = "nonmatchingid";

        // THEN
        assertThrows(Exception.class, () -> getGroupActivity.handleRequest(groupId));
    }

    @Test
    public void checkGetGroupActivity_getGroupRepeatedTimes_returnsSameGroupId() {
        // GIVEN
        Group group = testDataProvider.createGroup(testDataProvider.generateGroupName(), GroupType.DISCUSSION_GROUP);
        String groupId1 = group.getId();

        Group group2 = testDataProvider.createGroup(testDataProvider.generateGroupName(), GroupType.GAMING_GROUP);
        String groupId2 = group2.getId();

        // WHEN
        Group result1a = getGroupActivity.handleRequest(groupId1);
        Group result1b = getGroupActivity.handleRequest(groupId1);
        Group result1c = getGroupActivity.handleRequest(groupId1);

        Group result2a = getGroupActivity.handleRequest(groupId2);
        Group result2b = getGroupActivity.handleRequest(groupId2);
        Group result2c = getGroupActivity.handleRequest(groupId2);

        // THEN
        assertSame(result1a, result1b);
        assertSame(result1b, result1c);

        assertSame(result2a, result2b);
        assertSame(result2b, result2c);

        assertNotSame(result1a, result2a);
    }
}
