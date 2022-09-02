# Exploring `In-Memory Caching` Code Along

This activity will be walked through by your instructor.

**Your participation is critical to your understanding**, so please respond when asked by the instructor and ask any questions you may have immediately.

Please do not be afraid to ask questions.  Chances are very good that others have the same and/or similar questions and are bashful about asking.  They will appreciate your asking the question.

### Implementing the Caching Logic

This project is a continuation of the In Memory Caching Amazon Gaming Membership Guided Project.  

In the Guided Project, you followed along in implementing a caching design using Google Guava to cache already calculated results of a User being in a Group.

In this Code Along, you will implement the caching design in order to cache the Ids of Groups, currently written in a non-caching form in **GroupDao**. You will be identifying any similar patterns that can be utilized in your own caching programs while also identifying any differences which may occur from one use-case to another.

### Debugging the Cache

You will utilize breakpoints to watch the flow of execution of the code when the Cache has a **miss** versus a **hit**.


## Special Requirements

*Note: If you created these tables during the In Memory Caching Guided Project, these requirements should already be met.*
1. Create the tables we'll be using for this activity by running these aws CLI commands:
   ```none
   aws cloudformation create-stack --region us-west-2 --stack-name caching-groups-table --template-body file://cloudformation/groups_table.yaml --capabilities CAPABILITY_IAM
   aws cloudformation create-stack --region us-west-2 --stack-name caching-groupmemberships-table --template-body file://cloudformation/group_memberships_table.yaml --capabilities CAPABILITY_IAM
   aws cloudformation create-stack --region us-west-2 --stack-name caching-groupmembershipaudits --template-body file://cloudformation/group_membership_audits_table.yaml --capabilities CAPABILITY_IAM
   ```
2. Log into your AWS account and verify that the tables exist and have
   sample data.
3. Review the different attributes to make sure you understand
   what they represent.
4. As a final verification, run `Phase0Test` and `Phase1Test` to make sure they pass.


## Requirements

- IntelliJ with Java 11
- If you're running the code from your terminal, make sure you have Java 11 installed on your machine (not just in IntelliJ)

## Set Up

- Clone and open this repo in IntelliJ.
