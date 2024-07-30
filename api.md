# Scrum Game API

<details>
  <summary><strong>Table of Contents</strong></summary>

* [Query](#query)
* [Mutation](#mutation)
* [Objects](#objects)
    * [Achievement](#achievement)
    * [AchievementProgress](#achievementprogress)
    * [AnimalVoting](#animalvoting)
    * [AnimalVotingState](#animalvotingstate)
    * [BasicUserInfo](#basicuserinfo)
    * [CodeRepositorySettings](#coderepositorysettings)
    * [DefaultDataField](#defaultdatafield)
    * [DefaultEvent](#defaultevent)
    * [DefaultEventType](#defaulteventtype)
    * [DefaultFieldSchemaDefinition](#defaultfieldschemadefinition)
    * [DefaultSchemaDefinition](#defaultschemadefinition)
    * [DefinitionOfDoneItem](#definitionofdoneitem)
    * [EstimationStats](#estimationstats)
    * [EstimationVote](#estimationvote)
    * [GlobalUser](#globaluser)
    * [GlobalUserRole](#globaluserrole)
    * [Icon](#icon)
    * [ImsSettings](#imssettings)
    * [Issue](#issue)
    * [IssueEstimation](#issueestimation)
    * [IssueMutation](#issuemutation)
    * [IssuePriorityConfiguration](#issuepriorityconfiguration)
    * [IssueState](#issuestate)
    * [IssueStateInBoard](#issuestateinboard)
    * [IssueType](#issuetype)
    * [IssueTypeConfiguration](#issuetypeconfiguration)
    * [MeetingAttendee](#meetingattendee)
    * [NameVoting](#namevoting)
    * [NameVotingState](#namevotingstate)
    * [PaginationInfo](#paginationinfo)
    * [PlacedAsset](#placedasset)
    * [PlanningMeeting](#planningmeeting)
    * [PlanningMeetingMutation](#planningmeetingmutation)
    * [PlanningSettings](#planningsettings)
    * [Project](#project)
    * [ProjectBoard](#projectboard)
    * [ProjectMutation](#projectmutation)
    * [ProjectRole](#projectrole)
    * [ProjectSettings](#projectsettings)
    * [Reaction](#reaction)
    * [RepositoryDefinition](#repositorydefinition)
    * [RetrospectiveActivity](#retrospectiveactivity)
    * [RetrospectiveColumn](#retrospectivecolumn)
    * [RetrospectiveComment](#retrospectivecomment)
    * [RetrospectiveMeeting](#retrospectivemeeting)
    * [RetrospectiveMeetingMutation](#retrospectivemeetingmutation)
    * [ShopItem](#shopitem)
    * [Sprint](#sprint)
    * [SprintGoalVoting](#sprintgoalvoting)
    * [SprintStats](#sprintstats)
    * [SprintUserStats](#sprintuserstats)
    * [StandupMeeting](#standupmeeting)
    * [StandupMeetingMutation](#standupmeetingmutation)
    * [StandupMeetingSettings](#standupmeetingsettings)
    * [Subscription](#subscription)
    * [UserInProject](#userinproject)
    * [UserStats](#userstats)
    * [Vote](#vote)
* [Inputs](#inputs)
    * [CodeRepositorySettingsInput](#coderepositorysettingsinput)
    * [CreateEventInput](#createeventinput)
    * [CreateEventTypeInput](#createeventtypeinput)
    * [CreateGlobalUserInput](#createglobaluserinput)
    * [CreateGlobalUserRoleInput](#createglobaluserroleinput)
    * [CreateIssueInput](#createissueinput)
    * [CreateProjectInput](#createprojectinput)
    * [CreateProjectRoleInput](#createprojectroleinput)
    * [CreateSprintInput](#createsprintinput)
    * [DataFieldInput](#datafieldinput)
    * [DateTimeFilter](#datetimefilter)
    * [DefinitionOfDoneConfirmState](#definitionofdoneconfirmstate)
    * [DefinitionOfDoneItemInput](#definitionofdoneiteminput)
    * [FieldSchemaDefinitionInput](#fieldschemadefinitioninput)
    * [IconInput](#iconinput)
    * [ImsSettingsInput](#imssettingsinput)
    * [IntFilter](#intfilter)
    * [IssuePriorityInput](#issuepriorityinput)
    * [IssueStateInput](#issuestateinput)
    * [IssueTypeInput](#issuetypeinput)
    * [Pagination](#pagination)
    * [PlaceAssetInput](#placeassetinput)
    * [PlanningMeetingInput](#planningmeetinginput)
    * [PlanningSettingsInput](#planningsettingsinput)
    * [ProjectSettingsInput](#projectsettingsinput)
    * [RepositoryDefinitionInput](#repositorydefinitioninput)
    * [RetrospectiveActivityInput](#retrospectiveactivityinput)
    * [RetrospectiveColumnInput](#retrospectivecolumninput)
    * [RetrospectiveMeetingInput](#retrospectivemeetinginput)
    * [SchemaDefinitionInput](#schemadefinitioninput)
    * [StandupMeetingInput](#standupmeetinginput)
    * [StandupMeetingSettingsInput](#standupmeetingsettingsinput)
    * [StringFilter](#stringfilter)
    * [UpdateEventTypeInput](#updateeventtypeinput)
    * [UpdateGlobalUserInput](#updateglobaluserinput)
    * [UpdateGlobalUserRoleInput](#updateglobaluserroleinput)
    * [UpdateProjectInput](#updateprojectinput)
    * [UpdateProjectRoleInput](#updateprojectroleinput)
    * [UpdateSprintInput](#updatesprintinput)
* [Enums](#enums)
    * [AllowedDataType](#alloweddatatype)
    * [Animal](#animal)
    * [EventVisibility](#eventvisibility)
    * [GlobalPrivilege](#globalprivilege)
    * [IssuePriority](#issuepriority)
    * [IssueStateType](#issuestatetype)
    * [KnownAsset](#knownasset)
    * [KnownIcon](#knownicon)
    * [MeetingRole](#meetingrole)
    * [MeetingType](#meetingtype)
    * [PlanningMeetingPage](#planningmeetingpage)
    * [ProjectPrivilege](#projectprivilege)
    * [RetrospectiveMeetingPage](#retrospectivemeetingpage)
    * [SortDirection](#sortdirection)
    * [SprintSuccessState](#sprintsuccessstate)
    * [TShirtSizeEstimation](#tshirtsizeestimation)
    * [UserState](#userstate)
* [Scalars](#scalars)
    * [Boolean](#boolean)
    * [Date](#date)
    * [DateTime](#datetime)
    * [Float](#float)
    * [ID](#id)
    * [Int](#int)
    * [LocalTime](#localtime)
    * [String](#string)
    * [Time](#time)
    * [UUID](#uuid)
    * [Url](#url)
* [Interfaces](#interfaces)
    * [DataField](#datafield)
    * [Event](#event)
    * [EventType](#eventtype)
    * [FieldSchemaDefinition](#fieldschemadefinition)
    * [Meeting](#meeting)
    * [SchemaDefinition](#schemadefinition)

</details>

## Query
<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>projects</strong></td>
<td valign="top">[<a href="#project">Project</a>!]!</td>
<td>

Get all projects

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a></td>
<td>

Get a project by its ID

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalUsers</strong></td>
<td valign="top">[<a href="#globaluser">GlobalUser</a>!]!</td>
<td>

Get all global users

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalUser</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td>

Get a global user by its ID

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentUser</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td>

Get the current user

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentUserInfo</strong></td>
<td valign="top"><a href="#basicuserinfo">BasicUserInfo</a></td>
<td>

Get basic user information about the current user, currently fetched from Gropius

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalUserRoles</strong></td>
<td valign="top">[<a href="#globaluserrole">GlobalUserRole</a>!]!</td>
<td>

Get all global user roles

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalUserRole</strong></td>
<td valign="top"><a href="#globaluserrole">GlobalUserRole</a></td>
<td>

Get a global user role by its name

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventTypes</strong></td>
<td valign="top">[<a href="#eventtype">EventType</a>!]!</td>
<td>

Get all event types

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventType</strong></td>
<td valign="top"><a href="#eventtype">EventType</a></td>
<td>

Get an event type by its ID

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

## Mutation
<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>createProject</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td>

Creates a new project. Requires the permission to create a project.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createprojectinput">CreateProjectInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateProject</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td>

Updates an existing project. Requires the permission to update a project.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#updateprojectinput">UpdateProjectInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>deleteProject</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Deletes a project. Requires the permission to delete a project.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutateProject</strong></td>
<td valign="top"><a href="#projectmutation">ProjectMutation</a>!</td>
<td>

Perform changes on a project.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>register</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td>

Registers a new global user.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createglobaluserinput">CreateGlobalUserInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>joinProject</strong></td>
<td valign="top"><a href="#userinproject">UserInProject</a>!</td>
<td>

Adds a user to a project.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateGlobalUser</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td>

Updates a global user.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#updateglobaluserinput">UpdateGlobalUserInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>grantRole</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td>

Grants a role to a user. The caller must have all permissions that are required by the role
to grant it to another user.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">userId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">roleName</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>revokeRole</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td>

Revokes a role from a user.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">userId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">roleName</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>deleteUser</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Deletes a user. Requires the permission to delete a user.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createGlobalUserRole</strong></td>
<td valign="top"><a href="#globaluserrole">GlobalUserRole</a>!</td>
<td>

Creates a new user role.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createglobaluserroleinput">CreateGlobalUserRoleInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateGlobalUserRole</strong></td>
<td valign="top"><a href="#globaluserrole">GlobalUserRole</a>!</td>
<td>

Updates a user role.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#updateglobaluserroleinput">UpdateGlobalUserRoleInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>deleteGlobalUserRole</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Deletes a user role.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createEventType</strong></td>
<td valign="top"><a href="#eventtype">EventType</a>!</td>
<td>

Creates a new event type.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createeventtypeinput">CreateEventTypeInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateEventType</strong></td>
<td valign="top"><a href="#eventtype">EventType</a>!</td>
<td>

Updates an event type. This can only update event types created manually, not the predefined ones.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#updateeventtypeinput">UpdateEventTypeInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>deleteEventType</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Deletes an event type. This can only delete event types created manually, not the predefined ones.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

## Objects

### Achievement

An achievement is a goal that a user can achieve. It has a name, a description, a goal, and an icon.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>identifier</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Identifier of the achievement.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the achievement.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Description of the achievement.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>goal</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The target number of the achievement, e.g., the number of times a certain action has to be performed.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>icon</strong></td>
<td valign="top"><a href="#icon">Icon</a></td>
<td>

Icon of the achievement. Frontend only accepts emojis.

</td>
</tr>
</tbody>
</table>

### AchievementProgress

The progress of an achievement.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>achievement</strong></td>
<td valign="top"><a href="#achievement">Achievement</a>!</td>
<td>

The achievement that is being tracked.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>progress</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

Number of times the associated action has been performed.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>achieved</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Whether the achievement has been achieved.

</td>
</tr>
</tbody>
</table>

### AnimalVoting

Details about the animal voting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>votableAnimals</strong></td>
<td valign="top">[<a href="#animal">Animal</a>!]!</td>
<td>

The animals that can be voted for.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>animalVotingStates</strong></td>
<td valign="top">[<a href="#animalvotingstate">AnimalVotingState</a>!]!</td>
<td>

The voting states for each animal, containing the votes of the users.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finished</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

If the animal voting is finished.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>votingResult</strong></td>
<td valign="top"><a href="#animal">Animal</a></td>
<td>

If the animal voting is finished, the result of the voting.

</td>
</tr>
</tbody>
</table>

### AnimalVotingState

Holds the votes for an animal.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>votedFor</strong></td>
<td valign="top"><a href="#animal">Animal</a>!</td>
<td>

The animal that is voted for.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userVotes</strong></td>
<td valign="top">[<a href="#vote">Vote</a>!]!</td>
<td>

The votes for the animal.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>totalVotes</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The total number of votes for the animal.

</td>
</tr>
</tbody>
</table>

### BasicUserInfo

User info from the AuthenticationProvider

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

The ID of the user.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>isAdmin</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

If the user should be treated as an admin.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>username</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

The username of the user.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>avatar</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

The avatar of the user. Should be a URL.

</td>
</tr>
</tbody>
</table>

### CodeRepositorySettings

Settings related to the code repositories of a project.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>repositories</strong></td>
<td valign="top">[<a href="#repositorydefinition">RepositoryDefinition</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### DefaultDataField

Default implementation of the DataField interface.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>key</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#alloweddatatype">AllowedDataType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>value</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
</tbody>
</table>

### DefaultEvent

Implementation of the Event interface.
Fields are described in the interface definition.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventType</strong></td>
<td valign="top"><a href="#defaulteventtype">DefaultEventType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>visibility</strong></td>
<td valign="top"><a href="#eventvisibility">EventVisibility</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>visibleToUserIds</strong></td>
<td valign="top">[<a href="#uuid">UUID</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>timestamp</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>message</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mostRecentChildTimestamp</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>parent</strong></td>
<td valign="top"><a href="#defaultevent">DefaultEvent</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventData</strong></td>
<td valign="top">[<a href="#defaultdatafield">DefaultDataField</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>children</strong></td>
<td valign="top">[<a href="#defaultevent">DefaultEvent</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td>

The user who is associated with this event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>reactions</strong></td>
<td valign="top">[<a href="#reaction">Reaction</a>!]!</td>
<td>

List of reactions on this event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>field</strong></td>
<td valign="top"><a href="#datafield">DataField</a></td>
<td>

Get a field by name.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>xpForCurrentUser</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

How much XP the current user has earned for this event.

</td>
</tr>
</tbody>
</table>

### DefaultEventType

Implementation of the EventType interface.
Fields are described in the interface definition.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>identifier</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>defaultVisibility</strong></td>
<td valign="top"><a href="#eventvisibility">EventVisibility</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventSchema</strong></td>
<td valign="top"><a href="#defaultschemadefinition">DefaultSchemaDefinition</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>messageTemplate</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### DefaultFieldSchemaDefinition

Default implementation of the FieldSchemaDefinition interface.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#alloweddatatype">AllowedDataType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>required</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>allowedValues</strong></td>
<td valign="top">[<a href="#string">String</a>!]</td>
<td></td>
</tr>
</tbody>
</table>

### DefaultSchemaDefinition

Default implementation of the SchemaDefinition interface.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>fields</strong></td>
<td valign="top">[<a href="#defaultfieldschemadefinition">DefaultFieldSchemaDefinition</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### DefinitionOfDoneItem

A single item of the definition of done that has to be checked or commented on when completing an issue.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>text</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

The text of the item.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>required</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Whether the item is required to be checked. If not, the dialog can be completed without checking this item.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>implies</strong></td>
<td valign="top">[<a href="#definitionofdoneitem">DefinitionOfDoneItem</a>!]!</td>
<td>

Items that are implied by this item. If this item is checked, all items in this list also need to be checked if they are
required.

</td>
</tr>
</tbody>
</table>

### EstimationStats

Statistics about the estimations.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>median</strong></td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>max</strong></td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>min</strong></td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mode</strong></td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a></td>
<td></td>
</tr>
</tbody>
</table>

### EstimationVote

Holds the votes for an estimation.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>votedFor</strong></td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userVotes</strong></td>
<td valign="top">[<a href="#vote">Vote</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>totalVotes</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### GlobalUser

A user of DinoDev. (In contrast to UserInProject, which is a user in a specific project.)

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>username</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Username of the user.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>avatar</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Avatar of the user. Must be a valid URL.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>vcsUserId</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

ID of the user in the code repository system, e.g. GitHub.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>roles</strong></td>
<td valign="top">[<a href="#globaluserrole">GlobalUserRole</a>!]!</td>
<td>

Roles of the user.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userInProject</strong></td>
<td valign="top"><a href="#userinproject">UserInProject</a></td>
<td>

Gets the corresponding UserInProject object for the user in the given project.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userInProjects</strong></td>
<td valign="top">[<a href="#userinproject">UserInProject</a>!]!</td>
<td>

Gets all UserInProject objects, i.e., the edges between the user and the projects.

</td>
</tr>
</tbody>
</table>

### GlobalUserRole

Global roles define the permissions of a user on a global level.
They are not tied to a specific project.

Currently, there is only a distinction between admins and non-admins.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalPrivileges</strong></td>
<td valign="top">[<a href="#globalprivilege">GlobalPrivilege</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### Icon

A single icon that can be displayed in the UI.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>path</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mdiIcon</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>url</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>knownIcon</strong></td>
<td valign="top"><a href="#knownicon">KnownIcon</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>emoji</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
</tbody>
</table>

### ImsSettings

Ims specific settings of a project. Currently, this is tailored to Gropius.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>imsName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the IMS.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsIcon</strong></td>
<td valign="top"><a href="#icon">Icon</a></td>
<td>

Icon of the IMS. (currently not used!)

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsProjectId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td>

ID of the project in the IMS.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsProjectUrl</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

URL of the project in the IMS.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueStates</strong></td>
<td valign="top">[<a href="#issuestate">IssueState</a>!]!</td>
<td>

Sorted list of issue states.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsIssueTemplateId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td>

Id of the issue template in the IMS.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>effortEstimationFieldName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the field in the IMS that contains the effort estimation.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintFieldName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the field in the IMS that contains the sprint number.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>partOfRelationId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td>

ID of the relation that defines the part-of relation.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issuePriorities</strong></td>
<td valign="top">[<a href="#issuepriorityconfiguration">IssuePriorityConfiguration</a>!]!</td>
<td>

Issue priorities.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueTypes</strong></td>
<td valign="top">[<a href="#issuetypeconfiguration">IssueTypeConfiguration</a>!]!</td>
<td>

Issue types.

</td>
</tr>
</tbody>
</table>

### Issue

Represents an issue in a project management system.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td>

Unique identifier for the issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td>

Unique identifier of the project the issue belongs to.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>title</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Title of the issue, providing a brief summary.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Detailed description of the issue. Markdown is supported.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>state</strong></td>
<td valign="top"><a href="#issuestate">IssueState</a>!</td>
<td>

Current state of the issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#issuetype">IssueType</a>!</td>
<td>

Categorization of the issue by type.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>priority</strong></td>
<td valign="top"><a href="#issuepriority">IssuePriority</a>!</td>
<td>

Priority of the issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintNumber</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

Sprint number associated with the issue, if applicable.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPoints</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

Story points associated with the issue. If not set, the issue is considered to not have an estimation yet.
Might also be calculated based on the effort estimation.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>effortEstimation</strong></td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a></td>
<td>

Effort estimation associated with the issue. If not set, the issue is considered to not have an estimation yet.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>assigneeIds</strong></td>
<td valign="top">[<a href="#uuid">UUID</a>!]!</td>
<td>

List of user UUIDs representing the assignees of the issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueEvents</strong></td>
<td valign="top">[<a href="#event">Event</a>!]!</td>
<td>

List of events / timeline items of the issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>labels</strong></td>
<td valign="top">[<a href="#string">String</a>!]!</td>
<td>

Labels associated with the issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueUrl</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Url to the issue in the issue management system.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>childrenIssueIds</strong></td>
<td valign="top">[<a href="#id">ID</a>!]!</td>
<td>

Children issue ids of the issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>parentIssueId</strong></td>
<td valign="top"><a href="#id">ID</a></td>
<td>

Parent issue id of the issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprint</strong></td>
<td valign="top"><a href="#sprint">Sprint</a></td>
<td>

The sprint that this issue is in.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>assignees</strong></td>
<td valign="top">[<a href="#userinproject">UserInProject</a>]!</td>
<td>

The assignees of this issue.

</td>
</tr>
</tbody>
</table>

### IssueEstimation

Details about the issue estimation.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>planningMeeting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>votes</strong></td>
<td valign="top">[<a href="#estimationvote">EstimationVote</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finished</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueId</strong></td>
<td valign="top"><a href="#id">ID</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issue</strong></td>
<td valign="top"><a href="#issue">Issue</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>countdownSeconds</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>estimationStats</strong></td>
<td valign="top"><a href="#estimationstats">EstimationStats</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finalResult</strong></td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a></td>
<td></td>
</tr>
</tbody>
</table>

### IssueMutation

Mutation type to change issues.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td>

The project that the issue belongs to.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueId</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

The id of the issue that is being changed.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeIssueTitle</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td>

Change the title of the issue.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">title</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeIssueDescription</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td>

Change the description of the issue.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">description</td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeIssueState</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td>

Change the state of the issue.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">stateName</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeIssueType</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td>

Change the type of the issue.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">typeName</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeSprint</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td>

Change the sprint of the issue.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">sprintNumber</td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>commentOnIssue</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td>

Add a comment to the issue.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">comment</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">optionalParentId</td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finishIssue</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td>

Move an issue to the state with the given name and add a comment to the issue,
containing the definition of done confirm states.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">dodConfirmStates</td>
<td valign="top">[<a href="#definitionofdoneconfirmstate">DefinitionOfDoneConfirmState</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">doneStateName</td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>assignIssue</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td>

Assign the issue to the user with the given id.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">assigneeId</td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td></td>
</tr>
</tbody>
</table>

### IssuePriorityConfiguration

Establishes a mapping between an issue priority in the IMS and the corresponding priority in DinoDev.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>imsPriorityId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issuePriority</strong></td>
<td valign="top"><a href="#issuepriority">IssuePriority</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### IssueState

Represents the state of an issue within its lifecycle.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the state.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#issuestatetype">IssueStateType</a>!</td>
<td>

Type of the state, categorizing its position in the workflow.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsStateId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td>

Identifier for the state in an issue management system.

</td>
</tr>
</tbody>
</table>

### IssueStateInBoard

Represents a column in a Kanban board.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>state</strong></td>
<td valign="top"><a href="#issuestate">IssueState</a>!</td>
<td>

The issue state of the column.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectBoard</strong></td>
<td valign="top"><a href="#projectboard">ProjectBoard</a>!</td>
<td>

The project board that the column belongs to.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issues</strong></td>
<td valign="top">[<a href="#issue">Issue</a>!]!</td>
<td>

The issues in the column.

</td>
</tr>
</tbody>
</table>

### IssueType

Defines the type of an issue, such as bug, feature, etc.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the issue type.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsTypeId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td>

Identifier for the issue type in an issue management system.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Description of what the issue type entails.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>iconPath</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Path to an icon visually representing the issue type.

</td>
</tr>
</tbody>
</table>

### IssueTypeConfiguration

Establishes a mapping between an issue type in the IMS and the corresponding type in DinoDev.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsTypeId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### MeetingAttendee

A user that attends a meeting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>userId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td>

The id of the user.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>state</strong></td>
<td valign="top"><a href="#userstate">UserState</a>!</td>
<td>

The state of the user, e.g. online, offline or away.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>role</strong></td>
<td valign="top"><a href="#meetingrole">MeetingRole</a>!</td>
<td>

The role of the user.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td>

The user who is attending the meeting.

</td>
</tr>
</tbody>
</table>

### NameVoting

Details about the name voting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>votableNames</strong></td>
<td valign="top">[<a href="#string">String</a>!]!</td>
<td>

The names that can be voted for.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>nameVotingStates</strong></td>
<td valign="top">[<a href="#namevotingstate">NameVotingState</a>!]!</td>
<td>

The voting states for each name, containing the votes of the users.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finished</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

If the name voting is finished.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>votingResult</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

If the name voting is finished, the result of the voting.

</td>
</tr>
</tbody>
</table>

### NameVotingState

Holds the votes for a name.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>votedFor</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

The name that is voted for.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userVotes</strong></td>
<td valign="top">[<a href="#vote">Vote</a>!]!</td>
<td>

The votes for the name.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>totalVotes</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The total number of votes for the name.

</td>
</tr>
</tbody>
</table>

### PaginationInfo

Return type for information about paginated results.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>page</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The current page number.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>size</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The number of elements per page.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>totalElements</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The total number of elements across all pages.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>totalPages</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The total number of pages.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>hasNext</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Whether there is a next page.

</td>
</tr>
</tbody>
</table>

### PlacedAsset

Represents a placed decoration in the animal enclosure.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>asset</strong></td>
<td valign="top"><a href="#knownasset">KnownAsset</a>!</td>
<td>

The asset that has been placed.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>x</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The x-coordinate of the asset (center of the asset).

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>y</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The y-coordinate of the asset (center of the asset).

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>placedByUserId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td>

The user who placed the asset.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>placedBy</strong></td>
<td valign="top"><a href="#userinproject">UserInProject</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### PlanningMeeting

The PlanningMeeting represents the SCRUM planning meeting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>planningSettings</strong></td>
<td valign="top"><a href="#planningsettings">PlanningSettings</a>!</td>
<td>

The settings of the planning meeting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentPage</strong></td>
<td valign="top"><a href="#planningmeetingpage">PlanningMeetingPage</a>!</td>
<td>

The current page.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>animalVoting</strong></td>
<td valign="top"><a href="#animalvoting">AnimalVoting</a>!</td>
<td>

Details about the animal voting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>nameVoting</strong></td>
<td valign="top"><a href="#namevoting">NameVoting</a>!</td>
<td>

Details about the name voting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueEstimation</strong></td>
<td valign="top"><a href="#issueestimation">IssueEstimation</a>!</td>
<td>

Details about the current issue estimation.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintGoalVoting</strong></td>
<td valign="top"><a href="#sprintgoalvoting">SprintGoalVoting</a>!</td>
<td>

Details about the sprint goal voting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>meetingType</strong></td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td>

The type of the meeting, must be PLANNING.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>active</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

If the meeting is currently active.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>attendees</strong></td>
<td valign="top">[<a href="#meetingattendee">MeetingAttendee</a>!]!</td>
<td>

The attendees of the meeting.
Contains at most one meeting leader.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td>

The id of the project that the meeting is associated with.

</td>
</tr>
</tbody>
</table>

### PlanningMeetingMutation

Wrapper type for all operations on the planning meeting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changePage</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">page</td>
<td valign="top"><a href="#planningmeetingpage">PlanningMeetingPage</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>voteAnimal</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

Add a vote for an animal.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">animal</td>
<td valign="top"><a href="#animal">Animal</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>endAnimalVoting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

For meeting leader: End the animal voting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>addName</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

Adds a new name that can be voted for.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>voteName</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

Adds a vote for a name.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>endNameVoting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

For meeting leader: End the name voting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>voteEstimation</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

Vote for a t-shirt size on the current issue.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">estimation</td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>restartEstimation</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

For meeting leader: Restart the estimation of the current issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>nextIssue</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

For meeting leader: Selects an issue to estimate.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">issueId</td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>startCountdown</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

For meeting leader: Start a countdown for the estimation.
After the countdown, the estimation will end, as if "endEstimation" was called.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">seconds</td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>endEstimation</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

For meeting leader: End the estimation of the current issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>setFinalResult</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

Confirm the final estimation result.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">estimation</td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a>!</td>
<td>

The final estimation result.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">assignUserIds</td>
<td valign="top">[<a href="#uuid">UUID</a>!]!</td>
<td>

Optional: user ids that should be assigned to the issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>addSprintIssue</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

Adds an issue to the sprint goal.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">issueId</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>removeSprintIssue</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

Removes an issue from the sprint goal.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">issueId</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finishSprintGoalVoting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

For meeting leader: End the sprint goal voting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finishMeeting</strong></td>
<td valign="top"><a href="#sprint">Sprint</a>!</td>
<td>

For meeting leader: Finish the planning meeting.

</td>
</tr>
</tbody>
</table>

### PlanningSettings

The settings of the planning meeting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>sprintDurationDays</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

The duration of the sprint in days.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintStartDate</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td>

The start date of the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>customGoldChallengeReward</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

A custom reward for the gold challenge. (currently not used)

</td>
</tr>
</tbody>
</table>

### Project

Represents a student development project.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td>

Unique identifier of the project. (This is not the same ID as the one in the IMS.)

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the project.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Description of the project.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectSettings</strong></td>
<td valign="top"><a href="#projectsettings">ProjectSettings</a>!</td>
<td>

Project settings.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentSprintNumber</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

The number of the current sprint. Returns null if no sprint is active.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentSprint</strong></td>
<td valign="top"><a href="#sprint">Sprint</a></td>
<td>

The current sprint of the project. Returns null if no sprint is active.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>previousSprint</strong></td>
<td valign="top"><a href="#sprint">Sprint</a></td>
<td>

The previous sprint of the project. Returns null if no sprint is active.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprints</strong></td>
<td valign="top">[<a href="#sprint">Sprint</a>!]!</td>
<td>

All sprints of the project.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>users</strong></td>
<td valign="top">[<a href="#userinproject">UserInProject</a>!]!</td>
<td>

Returns the users of the project.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentUser</strong></td>
<td valign="top"><a href="#userinproject">UserInProject</a></td>
<td>

Return the currently logged in user in the project, defined by the JWT token.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>activePlanningMeeting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a></td>
<td>

Returns the planning meeting of the project if one is active, otherwise null.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>activeStandupMeeting</strong></td>
<td valign="top"><a href="#standupmeeting">StandupMeeting</a></td>
<td>

Returns the standup meeting of the project if one is active, otherwise null.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>activeRetrospectiveMeeting</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a></td>
<td>

Returns the retrospective meeting of the project if one is active, otherwise null.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>roles</strong></td>
<td valign="top">[<a href="#projectrole">ProjectRole</a>!]!</td>
<td>

Returns all project roles.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>role</strong></td>
<td valign="top"><a href="#projectrole">ProjectRole</a></td>
<td>

Returns the role with the given name.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issues</strong></td>
<td valign="top">[<a href="#issue">Issue</a>!]!</td>
<td>

Returns the issues of the project.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issue</strong></td>
<td valign="top"><a href="#issue">Issue</a></td>
<td>

Returns the issue with the given ID.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectBoard</strong></td>
<td valign="top"><a href="#projectboard">ProjectBoard</a>!</td>
<td>

Groups the issues of the project by their state, representing a Kanban board.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>events</strong></td>
<td valign="top">[<a href="#event">Event</a>!]!</td>
<td>

Returns the events of the project.

NOTE: This also triggers the synchronization of Gropius events.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">page</td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">size</td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>shopItems</strong></td>
<td valign="top">[<a href="#shopitem">ShopItem</a>!]!</td>
<td>

Returns the shop items of the project.

</td>
</tr>
</tbody>
</table>

### ProjectBoard

Represents a Kanban board.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td>

The project that the board belongs to.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>states</strong></td>
<td valign="top">[<a href="#issuestateinboard">IssueStateInBoard</a>!]!</td>
<td>

Ordered list of states in the board.

</td>
</tr>
</tbody>
</table>

### ProjectMutation

Wrapper that contains all project related mutations.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>joinMeeting</strong></td>
<td valign="top"><a href="#meeting">Meeting</a>!</td>
<td>

Adds the current user to a meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">type</td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>leaveMeeting</strong></td>
<td valign="top"><a href="#meeting">Meeting</a></td>
<td>

Marks the current user as offline in a meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">type</td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>cancelMeeting</strong></td>
<td valign="top"><a href="#meeting">Meeting</a>!</td>
<td>

Cancels a meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">type</td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>promoteToMeetingLeader</strong></td>
<td valign="top"><a href="#meeting">Meeting</a>!</td>
<td>

Promotes a user to a leader in a meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">type</td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">userId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>reactToEvent</strong></td>
<td valign="top"><a href="#event">Event</a>!</td>
<td>

Adds a reaction to an event.
Currently, the reaction string does not influence the behavior.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">eventId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">reaction</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>postComment</strong></td>
<td valign="top"><a href="#event">Event</a>!</td>
<td>

Adds a new global comment to an event.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">optionalParentEventId</td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td>

ID of the parent comment if the comment is a reply.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">comment</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createPlanningMeeting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td>

Starts a new planning meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#planningmeetinginput">PlanningMeetingInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutatePlanningMeeting</strong></td>
<td valign="top"><a href="#planningmeetingmutation">PlanningMeetingMutation</a>!</td>
<td>

Perform changes to the planning meeting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createStandupMeeting</strong></td>
<td valign="top"><a href="#standupmeeting">StandupMeeting</a>!</td>
<td>

Starts a new standup meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#standupmeetinginput">StandupMeetingInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutateStandupMeeting</strong></td>
<td valign="top"><a href="#standupmeetingmutation">StandupMeetingMutation</a>!</td>
<td>

Perform changes to the standup meeting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createRetrospectiveMeeting</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td>

Starts a new retrospective meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#retrospectivemeetinginput">RetrospectiveMeetingInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutateRetrospectiveMeeting</strong></td>
<td valign="top"><a href="#retrospectivemeetingmutation">RetrospectiveMeetingMutation</a>!</td>
<td>

Perform changes to the retrospective meeting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createRole</strong></td>
<td valign="top"><a href="#projectrole">ProjectRole</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createprojectroleinput">CreateProjectRoleInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateRole</strong></td>
<td valign="top"><a href="#projectrole">ProjectRole</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#updateprojectroleinput">UpdateProjectRoleInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>deleteRole</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createSprint</strong></td>
<td valign="top"><a href="#sprint">Sprint</a>!</td>
<td>

Creates a new sprint.
Remark: It is recommended to use the start new sprints via a planning meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createsprintinput">CreateSprintInput</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateSprint</strong></td>
<td valign="top"><a href="#sprint">Sprint</a>!</td>
<td>

Updates a sprint.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">number</td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#updatesprintinput">UpdateSprintInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutateIssue</strong></td>
<td valign="top"><a href="#issuemutation">IssueMutation</a>!</td>
<td>

Perform changes to an issue.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createIssue</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td>

Creates a new issue.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createissueinput">CreateIssueInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>resetUserStats</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Resets the user stats of the project. Requires the user to have the role "ADMIN".

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>resetAchievements</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Resets the achievements of the project. Requires the user to have the role "ADMIN".

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>buyAndPlace</strong></td>
<td valign="top"><a href="#placedasset">PlacedAsset</a>!</td>
<td>

Places a new decoration asset in the enclosure.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#placeassetinput">PlaceAssetInput</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### ProjectRole

Roles are used to define the permissions of a user in a project.
A user can have multiple roles in a project.

NOTE: Project-level privileges/roles are NOT implemented yet.
While the general framework is in place, an actual differentiation
between roles has yet to be implemented.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>gamifiedName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectPrivileges</strong></td>
<td valign="top">[<a href="#projectprivilege">ProjectPrivilege</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### ProjectSettings

Settings of a project.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>codeRepositorySettings</strong></td>
<td valign="top"><a href="#coderepositorysettings">CodeRepositorySettings</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsSettings</strong></td>
<td valign="top"><a href="#imssettings">ImsSettings</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>definitionOfDone</strong></td>
<td valign="top">[<a href="#definitionofdoneitem">DefinitionOfDoneItem</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### Reaction

Represents a like to an event.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>userId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>reaction</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### RepositoryDefinition

Definition of a code repository.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>url</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>icon</strong></td>
<td valign="top"><a href="#icon">Icon</a></td>
<td></td>
</tr>
</tbody>
</table>

### RetrospectiveActivity

Column-based activity in the retrospective meeting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>columns</strong></td>
<td valign="top">[<a href="#retrospectivecolumn">RetrospectiveColumn</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### RetrospectiveColumn

A category of comments in the retrospective meeting.
For example "Mad" in a "Mad, Sad, Glad" activity.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>title</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>comments</strong></td>
<td valign="top">[<a href="#retrospectivecomment">RetrospectiveComment</a>!]!</td>
<td>

Comments in this column.

</td>
</tr>
</tbody>
</table>

### RetrospectiveComment

A comment by a user in the retrospective activity.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>content</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>authorId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>thumbsUpBy</strong></td>
<td valign="top">[<a href="#uuid">UUID</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### RetrospectiveMeeting

Represents a retrospective meeting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>currentPage</strong></td>
<td valign="top"><a href="#retrospectivemeetingpage">RetrospectiveMeetingPage</a>!</td>
<td>

Current page of the retrospective meeting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>goldMedalUserId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td>

Id of the user who achieved the gold medal.
Can be null, if no user contributed to the project in the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>silverMedalUserId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td>

Id of the user who achieved the silver medal.
Can be null, if only one user contributed to the project in the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>bronzeMedalUserId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td>

Id of the user who achieved the bronze medal.
Can be null, if only two users contributed to the project in the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>goldMedalUser</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td>

User who achieved the gold medal.
Can be null, if no user contributed to the project in the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>silverMedalUser</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td>

User who achieved the silver medal.
Can be null, if only one user contributed to the project in the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>bronzeMedalUser</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td>

User who achieved the bronze medal.
Can be null, if only two users contributed to the project in the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>goldMedalPoints</strong></td>
<td valign="top"><a href="#float">Float</a></td>
<td>

Number of SP the gold medal user achieved.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>silverMedalPoints</strong></td>
<td valign="top"><a href="#float">Float</a></td>
<td>

Number of SP the silver medal user achieved.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>bronzeMedalPoints</strong></td>
<td valign="top"><a href="#float">Float</a></td>
<td>

Number of SP the bronze medal user achieved.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>goldChallengeReward</strong></td>
<td valign="top"><a href="#animal">Animal</a></td>
<td>

Reward for the gold challenge, if it was achieved and there are any rewards left to unlock.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>baseRewards</strong></td>
<td valign="top">[<a href="#knownasset">KnownAsset</a>!]!</td>
<td>

List of rewards. Given under any circumstances, but may be empty.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>streakRewards</strong></td>
<td valign="top">[<a href="#knownasset">KnownAsset</a>!]!</td>
<td>

List of rewards when a streak of two or more successful sprints is achieved.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>activities</strong></td>
<td valign="top">[<a href="#retrospectiveactivity">RetrospectiveActivity</a>!]!</td>
<td>

List of activities in the retrospective meeting.
Currently, the frontend only supports this list to have a length of 0 or 1.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>meetingType</strong></td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>active</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>attendees</strong></td>
<td valign="top">[<a href="#meetingattendee">MeetingAttendee</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### RetrospectiveMeetingMutation

Mutation type for retrospective meetings. Contains all mutations that can be performed on retrospective meetings.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updatePage</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td>

Updates the current page of the retrospective meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">page</td>
<td valign="top"><a href="#retrospectivemeetingpage">RetrospectiveMeetingPage</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>awardMedals</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td>

Should be called for the medal awarding to happen.
Adds badges to the users who achieved the medals and awards virtual currency.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>addComment</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td>

Adds a comment to the respective column in a retrospective activity.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">columnId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">content</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>editComment</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td>

Updates a comment in the retrospective meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">commentId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">content</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>deleteComment</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td>

Deletes a comment in the retrospective meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">commentId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>thumbsUpComment</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td>

Adds a thumbs-up to a comment in the retrospective meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">commentId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finishMeeting</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td>

Finishes the retrospective meeting.

</td>
</tr>
</tbody>
</table>

### ShopItem

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>price</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>image</strong></td>
<td valign="top"><a href="#knownasset">KnownAsset</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### Sprint

Represents a sprint in a project.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td>

The project this sprint belongs to.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>animal</strong></td>
<td valign="top"><a href="#animal">Animal</a></td>
<td>

The animal that represents this sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

The name of the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>number</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The number of the sprint. Starts with 1 and is incremented by 1 for each new sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>startDate</strong></td>
<td valign="top"><a href="#datetime">DateTime</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>endDate</strong></td>
<td valign="top"><a href="#datetime">DateTime</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPointsPlanned</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

The number of story points that are planned for this sprint.
This is null for sprints that have been created before using DinoDev.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>customGoldChallengeReward</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Custom reward for achieving the gold challenge.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issues</strong></td>
<td valign="top">[<a href="#issue">Issue</a>!]!</td>
<td>

Issues that are part of this sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>stats</strong></td>
<td valign="top"><a href="#sprintstats">SprintStats</a>!</td>
<td>

Stats for this sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>placedAssets</strong></td>
<td valign="top">[<a href="#placedasset">PlacedAsset</a>!]!</td>
<td>

Places assets in the animal enclosure.

</td>
</tr>
</tbody>
</table>

### SprintGoalVoting

Details about the sprint goal voting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>planningMeeting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintIssueIds</strong></td>
<td valign="top">[<a href="#id">ID</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>nonSprintIssueIds</strong></td>
<td valign="top">[<a href="#id">ID</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintIssues</strong></td>
<td valign="top">[<a href="#issue">Issue</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>nonSprintIssues</strong></td>
<td valign="top">[<a href="#issue">Issue</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finished</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### SprintStats

Stats for a sprint.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>Sprint</strong></td>
<td valign="top"><a href="#sprint">Sprint</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>successState</strong></td>
<td valign="top"><a href="#sprintsuccessstate">SprintSuccessState</a>!</td>
<td>

The success state of the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>streak</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The sprint streak is the number of consecutive sprints that have been successfully completed.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>totalStoryPoints</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The number of story points that have been completed.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>averageStoryPoints</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td>

The average number of story points per issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>percentageStoryPointsCompleted</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td>

The percentage of story points that have been completed.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>percentageStoryPointsInProgress</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td>

The percentage of story points that are in progress.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>percentageStoryPointsNotStarted</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td>

The percentage of story points that have not been started.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueCount</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The number of issues in this sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>daysLeft</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The number of days left in this sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>daysElapsed</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The number of days that have elapsed in this sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>percentageTimeElapsed</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td>

The percentage of time that has elapsed in this sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPointsByDay</strong></td>
<td valign="top">[<a href="#int">Int</a>!]!</td>
<td>

Array where each element represents the number of story points that have been completed
on the corresponding day of the sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>burnDown</strong></td>
<td valign="top">[<a href="#int">Int</a>!]</td>
<td>

Array where each element represents the number of story points left to complete
on the corresponding day of the sprint.
This can be used to create a burn down chart.
This is null if the sprint was not created using DinoDev.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userStats</strong></td>
<td valign="top">[<a href="#sprintuserstats">SprintUserStats</a>!]!</td>
<td>

Stats for each user in this sprint.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userStatsByUserId</strong></td>
<td valign="top"><a href="#sprintuserstats">SprintUserStats</a>!</td>
<td>

Stats for a specific user in this sprint.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">userId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### SprintUserStats

Stats for a user in a sprint.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>userId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPointsCompleted</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td>

The number of story points that have been completed by this user.

</td>
</tr>
</tbody>
</table>

### StandupMeeting

Represents a standup meeting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>standupMeetingSettings</strong></td>
<td valign="top"><a href="#standupmeetingsettings">StandupMeetingSettings</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>order</strong></td>
<td valign="top">[<a href="#meetingattendee">MeetingAttendee</a>!]!</td>
<td>

The randomly assigned order of attendees in the meeting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentAttendee</strong></td>
<td valign="top"><a href="#meetingattendee">MeetingAttendee</a></td>
<td>

The user whose turn it is to speak.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>meetingType</strong></td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>active</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>attendees</strong></td>
<td valign="top">[<a href="#meetingattendee">MeetingAttendee</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### StandupMeetingMutation

Mutation type for standup meetings. Contains all mutations that can be performed on standup meetings.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>startStandupMeeting</strong></td>
<td valign="top"><a href="#standupmeeting">StandupMeeting</a>!</td>
<td>

Starts the standup meeting.
This will determine the order of attendees randomly.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeCurrentAttendee</strong></td>
<td valign="top"><a href="#standupmeeting">StandupMeeting</a>!</td>
<td>

Changes the current attendee in the standup meeting.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">attendeeId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finishStandupMeeting</strong></td>
<td valign="top"><a href="#standupmeeting">StandupMeeting</a>!</td>
<td>

Finishes the standup meeting.

</td>
</tr>
</tbody>
</table>

### StandupMeetingSettings

Settings for a standup meeting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>countdownPerAttendee</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

The (optional) number of seconds each attendee has to speak.

</td>
</tr>
</tbody>
</table>

### Subscription

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>meeting</strong></td>
<td valign="top"><a href="#meeting">Meeting</a></td>
<td>

Returns the current meeting instance for the given project and meeting type.
This is updated in real-time when the meeting changes.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">meetingType</td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>planningMeeting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a></td>
<td>

Returns the current planning meeting instance for the given project.
This is updated in real-time when the meeting changes.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>standupMeeting</strong></td>
<td valign="top"><a href="#standupmeeting">StandupMeeting</a>!</td>
<td>

Returns the current standup meeting instance for the given project.
This is updated in real-time when the meeting changes.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>retrospectiveMeeting</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td>

Returns the current retrospective meeting instance for the given project.
This is updated in real-time when the meeting changes.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>event</strong></td>
<td valign="top"><a href="#event">Event</a>!</td>
<td>

Listens for new events for the given project and user.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">userId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### UserInProject

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>roles</strong></td>
<td valign="top">[<a href="#projectrole">ProjectRole</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userStats</strong></td>
<td valign="top"><a href="#userstats">UserStats</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>publicEvents</strong></td>
<td valign="top">[<a href="#event">Event</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">size</td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">page</td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>achievements</strong></td>
<td valign="top">[<a href="#achievementprogress">AchievementProgress</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentBadge</strong></td>
<td valign="top"><a href="#icon">Icon</a></td>
<td></td>
</tr>
</tbody>
</table>

### UserStats

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>xp</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>totalXp</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>xpToNextLevel</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>level</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issuesCompleted</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issuesCreated</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>commentsWritten</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>reactionsGiven</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>pullRequestsCreated</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>pullRequestsClosed</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>pullRequestsReviewed</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>goldMedals</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>silverMedals</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>bronzeMedals</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>virtualCurrency</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### Vote

Represents a vote for a name or animal.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>userId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td>

The id of the user who voted.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td>

The user who voted.

</td>
</tr>
</tbody>
</table>

## Inputs

### CodeRepositorySettingsInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>repositories</strong></td>
<td valign="top">[<a href="#repositorydefinitioninput">RepositoryDefinitionInput</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### CreateEventInput

Input for creating an event.
Fields are described in the Event interface.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventTypeIdentifier</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>visibility</strong></td>
<td valign="top"><a href="#eventvisibility">EventVisibility</a></td>
<td>

Visibility of the event. If not provided, the default visibility of the event type is used.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>timestamp</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>message</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Message of the event. If not provided, the message template of the event type is used and
the eventData is used to fill in the template.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventData</strong></td>
<td valign="top">[<a href="#datafieldinput">DataFieldInput</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>parentId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>visibleToUserIds</strong></td>
<td valign="top">[<a href="#uuid">UUID</a>!]</td>
<td></td>
</tr>
</tbody>
</table>

### CreateEventTypeInput

Input for creating an event type.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>identifier</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>defaultVisibility</strong></td>
<td valign="top"><a href="#eventvisibility">EventVisibility</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventSchema</strong></td>
<td valign="top"><a href="#schemadefinitioninput">SchemaDefinitionInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>messageTemplate</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### CreateGlobalUserInput

Input to register a new global user.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>username</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>avatar</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>vcsUserId</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### CreateGlobalUserRoleInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalPrivileges</strong></td>
<td valign="top">[<a href="#globalprivilege">GlobalPrivilege</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### CreateIssueInput

Input type for creating a new issue.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>title</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Title of the new issue.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Detailed description of the new issue. Can be GitLab-flavored markdown.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>stateName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the state the issue should be initially set to.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>typeName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the type the issue should be categorized under.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintNumber</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

Sprint number the issue is associated with, if applicable.

</td>
</tr>
</tbody>
</table>

### CreateProjectInput

Input to create a new project.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>startingSprintNumber</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

At which sprint number the project should start.
Useful if DinoDev is not used from the beginning of the project.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectSettings</strong></td>
<td valign="top"><a href="#projectsettingsinput">ProjectSettingsInput</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### CreateProjectRoleInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>gamifiedName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectPrivileges</strong></td>
<td valign="top">[<a href="#projectprivilege">ProjectPrivilege</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### CreateSprintInput

Input object to create a new sprint.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>startDate</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>endDate</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPointsPlanned</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>animal</strong></td>
<td valign="top"><a href="#animal">Animal</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>customGoldChallengeReward</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
</tbody>
</table>

### DataFieldInput

Input for creating a data field.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>key</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#alloweddatatype">AllowedDataType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>value</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
</tbody>
</table>

### DateTimeFilter

Filter for date values.
If multiple filters are specified, they are combined with AND.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>after</strong></td>
<td valign="top"><a href="#datetime">DateTime</a></td>
<td>

If specified, filters for dates after the specified value.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>before</strong></td>
<td valign="top"><a href="#datetime">DateTime</a></td>
<td>

If specified, filters for dates before the specified value.

</td>
</tr>
</tbody>
</table>

### DefinitionOfDoneConfirmState

Represents an item of the definition of done and with the information if it is checked or not and
the reason if it is not checked.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>dodText</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Text of the definition of done item.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>checked</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

If the item is checked.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>reasonIfNotChecked</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

The reason why the item is not checked.
Should be null if the item is checked.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>children</strong></td>
<td valign="top">[<a href="#definitionofdoneconfirmstate">DefinitionOfDoneConfirmState</a>!]!</td>
<td>

The child confirm states, containing the child items of the definition of done item.

</td>
</tr>
</tbody>
</table>

### DefinitionOfDoneItemInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>text</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>required</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>implies</strong></td>
<td valign="top">[<a href="#definitionofdoneiteminput">DefinitionOfDoneItemInput</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### FieldSchemaDefinitionInput

Input for creating and updating a field schema definition.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#alloweddatatype">AllowedDataType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>required</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>allowedValues</strong></td>
<td valign="top">[<a href="#string">String</a>!]</td>
<td></td>
</tr>
</tbody>
</table>

### IconInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>path</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mdiIcon</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>url</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>knownIcon</strong></td>
<td valign="top"><a href="#knownicon">KnownIcon</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>emoji</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
</tbody>
</table>

### ImsSettingsInput

Input for the IMS settings. For a documentation of the fields, see the corresponding fields in the ImsSettings type.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>imsName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsIcon</strong></td>
<td valign="top"><a href="#iconinput">IconInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsProjectId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsProjectUrl</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueStates</strong></td>
<td valign="top">[<a href="#issuestateinput">IssueStateInput</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsIssueTemplateId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>effortEstimationFieldName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintFieldName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>partOfRelationId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issuePriorities</strong></td>
<td valign="top">[<a href="#issuepriorityinput">IssuePriorityInput</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueTypes</strong></td>
<td valign="top">[<a href="#issuetypeinput">IssueTypeInput</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### IntFilter

Filter for integer values.
If multiple filters are specified, they are combined with AND.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>equals</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

An integer value to match exactly.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>greaterThan</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

If specified, filters for values greater than to the specified value.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>lessThan</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

If specified, filters for values less than to the specified value.

</td>
</tr>
</tbody>
</table>

### IssuePriorityInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>imsPriorityId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issuePriority</strong></td>
<td valign="top"><a href="#issuepriority">IssuePriority</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### IssueStateInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#issuestatetype">IssueStateType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsStateId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### IssueTypeInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsTypeId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### Pagination

Specifies the page size and page number for paginated results.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>page</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The page number, starting at 0.
If not specified, the default value is 0.
For values greater than 0, the page size must be specified.
If this value is larger than the number of pages, an empty page is returned.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>size</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td>

The number of elements per page.

</td>
</tr>
</tbody>
</table>

### PlaceAssetInput

Input for placing an asset in the animal enclosure.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>asset</strong></td>
<td valign="top"><a href="#knownasset">KnownAsset</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>x</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>y</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### PlanningMeetingInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>meetingLeaderId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>planningSettings</strong></td>
<td valign="top"><a href="#planningsettingsinput">PlanningSettingsInput</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### PlanningSettingsInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>sprintDurationDays</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintStartDate</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>goldChallengeCustomReward</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
</tbody>
</table>

### ProjectSettingsInput

Input for the project settings.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>codeRepositorySettings</strong></td>
<td valign="top"><a href="#coderepositorysettingsinput">CodeRepositorySettingsInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsSettings</strong></td>
<td valign="top"><a href="#imssettingsinput">ImsSettingsInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>definitionOfDone</strong></td>
<td valign="top">[<a href="#definitionofdoneiteminput">DefinitionOfDoneItemInput</a>!]!</td>
<td>

Definition of done items.

</td>
</tr>
</tbody>
</table>

### RepositoryDefinitionInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>url</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>icon</strong></td>
<td valign="top"><a href="#iconinput">IconInput</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### RetrospectiveActivityInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>columns</strong></td>
<td valign="top">[<a href="#retrospectivecolumninput">RetrospectiveColumnInput</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### RetrospectiveColumnInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>title</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### RetrospectiveMeetingInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>meetingLeaderId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>activities</strong></td>
<td valign="top">[<a href="#retrospectiveactivityinput">RetrospectiveActivityInput</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### SchemaDefinitionInput

Input for creating and updating a schema definition.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>fields</strong></td>
<td valign="top">[<a href="#fieldschemadefinitioninput">FieldSchemaDefinitionInput</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### StandupMeetingInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>standupMeetingSettings</strong></td>
<td valign="top"><a href="#standupmeetingsettingsinput">StandupMeetingSettingsInput</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>meetingLeaderId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### StandupMeetingSettingsInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>countdownPerAttendee</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
</tbody>
</table>

### StringFilter

Filter for string values.
If multiple filters are specified, they are combined with AND.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>equals</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

A string value to match exactly.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>contains</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

A string value that must be contained in the field that is being filtered.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>ignoreCase</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

If true, the filter is case-insensitive.

</td>
</tr>
</tbody>
</table>

### UpdateEventTypeInput

Input for updating an event type.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>defaultVisibility</strong></td>
<td valign="top"><a href="#eventvisibility">EventVisibility</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventSchema</strong></td>
<td valign="top"><a href="#schemadefinitioninput">SchemaDefinitionInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>messageTemplate</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### UpdateGlobalUserInput

Input to update a global user.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>username</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>avatar</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>vcsUserId</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### UpdateGlobalUserRoleInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>globalPrivileges</strong></td>
<td valign="top">[<a href="#globalprivilege">GlobalPrivilege</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### UpdateProjectInput

Input to update a project.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectSettings</strong></td>
<td valign="top"><a href="#projectsettingsinput">ProjectSettingsInput</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### UpdateProjectRoleInput

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>gamifiedName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectPrivileges</strong></td>
<td valign="top">[<a href="#projectprivilege">ProjectPrivilege</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### UpdateSprintInput

Input object to update an existing sprint.

<table>
<thead>
<tr>
<th colspan="2" align="left">Field</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>startDate</strong></td>
<td valign="top"><a href="#datetime">DateTime</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>endDate</strong></td>
<td valign="top"><a href="#datetime">DateTime</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPointsPlanned</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>animal</strong></td>
<td valign="top"><a href="#animal">Animal</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>customGoldChallengeReward</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
</tbody>
</table>

## Enums

### AllowedDataType

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>STRING</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>INTEGER</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DOUBLE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>BOOLEAN</strong></td>
<td></td>
</tr>
</tbody>
</table>

### Animal

All known animals that can be placed in the park.

Frontend supports TREX, TRICERATOPS, DODO, and PARAUSOLOPHUS.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>STEGOSAURUS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>TREX</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>BRONTOSAURUS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>TRICERATOPS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>ANKYLOSAURUS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>PARASAUROLOPHUS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DODO</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>MAMMOTH</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>GIANT_SLOTH</strong></td>
<td></td>
</tr>
</tbody>
</table>

### EventVisibility

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>INTERNAL</strong></td>
<td>

Internal events. Not visible to users. May be shown to administrators.

</td>
</tr>
<tr>
<td valign="top"><strong>PRIVATE</strong></td>
<td>

Only for the user who created the event and users in "visibleToUserIds".

</td>
</tr>
<tr>
<td valign="top"><strong>DETAIL</strong></td>
<td>

Detail level: visible to all users but only when viewing a specific object, not in the whole list of events.

</td>
</tr>
<tr>
<td valign="top"><strong>PUBLIC</strong></td>
<td>

Project detail level: visible to all users

</td>
</tr>
</tbody>
</table>

### GlobalPrivilege

Privileges on the global level, i.e., not tied to a specific project.
Currently, UPDATE_ROLE and LIST_USERS are not used.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>CREATE_PROJECT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>CREATE_ROLE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>UPDATE_ROLE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DELETE_ROLE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>READ_USER_PRIVATE_INFO</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>CHANGE_ROLES</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>UPDATE_USER</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DELETE_USER</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>LIST_USERS</strong></td>
<td></td>
</tr>
</tbody>
</table>

### IssuePriority

Enumeration of priorities for issues.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>LOW</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>MEDIUM</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>HIGH</strong></td>
<td></td>
</tr>
</tbody>
</table>

### IssueStateType

Enumeration of possible states an issue can be in during its lifecycle.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>BACKLOG</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>SPRINT_BACKLOG</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>IN_PROGRESS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DONE_SPRINT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DONE</strong></td>
<td></td>
</tr>
</tbody>
</table>

### KnownAsset

All known decorations that can be placed in the park.

Note: not all are yet supported by the frontend.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>WATER_PUDDLE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>CAVE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>TREES</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>TREE_WITH_ROCKS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>BUSHES_1</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>BUSHES_2</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>BUSHES_3</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>ROCK_1</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>ROCK_2</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>FEEDING_TROUGH</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>FLOWERS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>FOUNTAIN</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>CAVE_2</strong></td>
<td></td>
</tr>
</tbody>
</table>

### KnownIcon

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>GROPIUS</strong></td>
<td></td>
</tr>
</tbody>
</table>

### MeetingRole

The role of the user in a meeting.
Can be an attendee or the meeting leader.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>ATTENDEE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>MEETING_LEADER</strong></td>
<td></td>
</tr>
</tbody>
</table>

### MeetingType

The type of the meeting, which can be a planning, retrospective or standup meeting.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>PLANNING</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>RETROSPECTIVE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>STANDUP</strong></td>
<td></td>
</tr>
</tbody>
</table>

### PlanningMeetingPage

The pages of the planning meeting.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>INFORMATION</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>CHOOSE_ANIMAL</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>NAME_ANIMAL</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>ESTIMATE_ISSUES</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>SPRINT_GOAL</strong></td>
<td></td>
</tr>
</tbody>
</table>

### ProjectPrivilege

Privileges on the project level.

NOTE: This is NOT implemented yet. These are just suggestions.
Future work is needed to implement project-level privileges.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>READ_PROJECT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>UPDATE_PROJECT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DELETE_PROJECT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>CREATE_SPRINT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>UPDATE_SPRINT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DELETE_SPRINT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>CREATE_SHOP_ITEM</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>UPDATE_SHOP_ITEM</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DELETE_SHOP_ITEM</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>CREATE_USER</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>UPDATE_USER</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DELETE_USER</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>CREATE_ROLE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>UPDATE_ROLE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DELETE_ROLE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>READ_USER_PRIVATE_INFO</strong></td>
<td></td>
</tr>
</tbody>
</table>

### RetrospectiveMeetingPage

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>INFORMATION</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>SPRINT_RESULT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>MEDAL_CEREMONY</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>GAMES</strong></td>
<td></td>
</tr>
</tbody>
</table>

### SortDirection

Specifies the sort direction, either ascending or descending.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>ASC</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>DESC</strong></td>
<td></td>
</tr>
</tbody>
</table>

### SprintSuccessState

A sprint can have different success states:

- IN_PROGRESS: The sprint is currently in progress
- UNKNOWN: The success state is unknown
- FAILED: The sprint has failed, i.e. not all story points have been completed
- SUCCESS: The sprint has been successfully completed
- SUCCESS_WITH_GOLD_CHALLENGE: The sprint has been successfully completed and the gold challenge has been achieved

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>IN_PROGRESS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>UNKNOWN</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>FAILED</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>SUCCESS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>SUCCESS_WITH_GOLD_CHALLENGE</strong></td>
<td></td>
</tr>
</tbody>
</table>

### TShirtSizeEstimation

Enumeration of possible sizes for a T-shirt estimation.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>XS</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>S</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>M</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>L</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>XL</strong></td>
<td></td>
</tr>
</tbody>
</table>

### UserState

The state of the user in a meeting.
Can be online, offline or away.
Away is currently not used.

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>ONLINE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>OFFLINE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>AWAY</strong></td>
<td></td>
</tr>
</tbody>
</table>

## Scalars

### Boolean

The `Boolean` scalar type represents `true` or `false`.

### Date

### DateTime

### Float

The `Float` scalar type represents signed double-precision fractional values as specified
by [IEEE 754](https://en.wikipedia.org/wiki/IEEE_floating_point).

### ID

The `ID` scalar type represents a unique identifier, often used to refetch an object or as key for a cache. The ID type
appears in a JSON response as a String; however, it is not intended to be human-readable. When expected as an input
type, any string (such as `"4"`) or integer (such as `4`) input value will be accepted as an ID.

### Int

The `Int` scalar type represents non-fractional signed whole numeric values. Int can represent values between -(2^31)
and 2^31 - 1.

### LocalTime

### String

The `String` scalar type represents textual data, represented as UTF-8 character sequences. The String type is most
often used by GraphQL to represent free-form human-readable text.

### Time

### UUID

### Url


## Interfaces

### DataField

Represents a field in the data of an event.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>key</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the field.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#alloweddatatype">AllowedDataType</a>!</td>
<td>

Type of the field.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>value</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Value of the field, as a string.

</td>
</tr>
</tbody>
</table>

### Event

Represents an event in the system.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td>

Unique identifier of the event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventType</strong></td>
<td valign="top"><a href="#eventtype">EventType</a>!</td>
<td>

Type of the event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>visibility</strong></td>
<td valign="top"><a href="#eventvisibility">EventVisibility</a>!</td>
<td>

Visibility of the event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td>

Id of the user who triggered the event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>visibleToUserIds</strong></td>
<td valign="top">[<a href="#uuid">UUID</a>!]!</td>
<td>

List of user ids who can see the event (in addition to the user who triggered the event).

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td>

Id of the project the event belongs to.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>timestamp</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td>

Timestamp of the event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>message</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Message of the event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mostRecentChildTimestamp</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td>

Timestamp of the most recent child event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>parent</strong></td>
<td valign="top"><a href="#event">Event</a></td>
<td>

Parent event, if any.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventData</strong></td>
<td valign="top">[<a href="#datafield">DataField</a>!]!</td>
<td>

Data of the event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>children</strong></td>
<td valign="top">[<a href="#event">Event</a>!]!</td>
<td>

List of child events.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td>

The user who is associated with this event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>reactions</strong></td>
<td valign="top">[<a href="#reaction">Reaction</a>!]!</td>
<td>

List of reactions on this event.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>field</strong></td>
<td valign="top"><a href="#datafield">DataField</a></td>
<td>

Get a field by name.

</td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>xpForCurrentUser</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td>

How much XP the current user has earned for this event.

</td>
</tr>
</tbody>
</table>

### EventType

Represents a type of event in the system.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>identifier</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Unique, human readable identifier of the event type.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Description of the event type.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>defaultVisibility</strong></td>
<td valign="top"><a href="#eventvisibility">EventVisibility</a>!</td>
<td>

Default of the event type.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventSchema</strong></td>
<td valign="top"><a href="#schemadefinition">SchemaDefinition</a>!</td>
<td>

Describes the schema of the event data.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>messageTemplate</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

String template for the message of the event.

</td>
</tr>
</tbody>
</table>

### FieldSchemaDefinition

Schema definition for a field in the event data.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td>

Name of the field.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#alloweddatatype">AllowedDataType</a>!</td>
<td>

Type of the field.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td>

Description of the field.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>required</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

Whether the field is required.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>allowedValues</strong></td>
<td valign="top">[<a href="#string">String</a>!]</td>
<td>

Allowed values for the field. Set to null if any value is allowed.

</td>
</tr>
</tbody>
</table>

### Meeting

Represents a SCRUM meeting.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>meetingType</strong></td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td>

The type of the meeting.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>active</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td>

If the meeting is currently active.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>attendees</strong></td>
<td valign="top">[<a href="#meetingattendee">MeetingAttendee</a>!]!</td>
<td>

The attendees of the meeting.
Contains at most one meeting leader.

</td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td>

The id of the project that the meeting is associated with.

</td>
</tr>
</tbody>
</table>

### SchemaDefinition

Definition of the schema of the event data.

<table>
<thead>
<tr>
<th align="left">Field</th>
<th align="right">Argument</th>
<th align="left">Type</th>
<th align="left">Description</th>
</tr>
</thead>
<tbody>
<tr>
<td colspan="2" valign="top"><strong>fields</strong></td>
<td valign="top">[<a href="#fieldschemadefinition">FieldSchemaDefinition</a>!]!</td>
<td>

List of fields in the schema.

</td>
</tr>
</tbody>
</table>
