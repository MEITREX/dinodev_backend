# Scrum Game API

<details>
  <summary><strong>Table of Contents</strong></summary>

  * [Query](#query)
  * [Mutation](#mutation)
  * [Objects](#objects)
    * [AnimalVoting](#animalvoting)
    * [AnimalVotingState](#animalvotingstate)
    * [CodeRepositorySettings](#coderepositorysettings)
    * [DefaultEvent](#defaultevent)
    * [DefaultEventType](#defaulteventtype)
    * [DefaultFieldSchemaDefinition](#defaultfieldschemadefinition)
    * [DefaultSchemaDefinition](#defaultschemadefinition)
    * [DefaultTemplateField](#defaulttemplatefield)
    * [DefinitionOfDoneItem](#definitionofdoneitem)
    * [EstimationStats](#estimationstats)
    * [EstimationVote](#estimationvote)
    * [GlobalUser](#globaluser)
    * [GlobalUserRole](#globaluserrole)
    * [ImsSettings](#imssettings)
    * [ImsUser](#imsuser)
    * [Issue](#issue)
    * [IssueEstimation](#issueestimation)
    * [IssueMutation](#issuemutation)
    * [IssuePriorityConfiguration](#issuepriorityconfiguration)
    * [IssueState](#issuestate)
    * [IssueStateInBoard](#issuestateinboard)
    * [IssueType](#issuetype)
    * [MeetingAttendee](#meetingattendee)
    * [MvelRule](#mvelrule)
    * [NameVoting](#namevoting)
    * [NameVotingState](#namevotingstate)
    * [PaginationInfo](#paginationinfo)
    * [PlanningMeeting](#planningmeeting)
    * [PlanningMeetingMutation](#planningmeetingmutation)
    * [PlanningSettings](#planningsettings)
    * [PrivateUserInfo](#privateuserinfo)
    * [Project](#project)
    * [ProjectBoard](#projectboard)
    * [ProjectMutation](#projectmutation)
    * [ProjectRole](#projectrole)
    * [ProjectSettings](#projectsettings)
    * [RetrospectiveMeeting](#retrospectivemeeting)
    * [RetrospectiveMeetingMutation](#retrospectivemeetingmutation)
    * [ShopItem](#shopitem)
    * [Sprint](#sprint)
    * [SprintGoalVoting](#sprintgoalvoting)
    * [SprintStats](#sprintstats)
    * [StandupMeeting](#standupmeeting)
    * [StandupMeetingMutation](#standupmeetingmutation)
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
    * [DateTimeFilter](#datetimefilter)
    * [DefinitionOfDoneConfirmState](#definitionofdoneconfirmstate)
    * [DefinitionOfDoneItemInput](#definitionofdoneiteminput)
    * [EventFilter](#eventfilter)
    * [FieldSchemaDefinitionInput](#fieldschemadefinitioninput)
    * [ImsSettingsInput](#imssettingsinput)
    * [IntFilter](#intfilter)
    * [IssuePriorityInput](#issuepriorityinput)
    * [IssueStateInput](#issuestateinput)
    * [Pagination](#pagination)
    * [PlanningMeetingInput](#planningmeetinginput)
    * [PlanningSettingsInput](#planningsettingsinput)
    * [ProjectSettingsInput](#projectsettingsinput)
    * [RetrospectiveMeetingInput](#retrospectivemeetinginput)
    * [SchemaDefinitionInput](#schemadefinitioninput)
    * [StandupMeetingInput](#standupmeetinginput)
    * [StringFilter](#stringfilter)
    * [TemplateFieldInput](#templatefieldinput)
    * [UpdateEventTypeInput](#updateeventtypeinput)
    * [UpdateGlobalUserInput](#updateglobaluserinput)
    * [UpdateGlobalUserRoleInput](#updateglobaluserroleinput)
    * [UpdateProjectInput](#updateprojectinput)
    * [UpdateProjectRoleInput](#updateprojectroleinput)
  * [Enums](#enums)
    * [AllowedDataType](#alloweddatatype)
    * [Animal](#animal)
    * [EventVisibility](#eventvisibility)
    * [GlobalPrivilege](#globalprivilege)
    * [IssuePriority](#issuepriority)
    * [IssueStateType](#issuestatetype)
    * [MeetingRole](#meetingrole)
    * [MeetingType](#meetingtype)
    * [PlanningMeetingPage](#planningmeetingpage)
    * [ProjectPrivilege](#projectprivilege)
    * [SortDirection](#sortdirection)
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
    * [Event](#event)
    * [EventType](#eventtype)
    * [FieldSchemaDefinition](#fieldschemadefinition)
    * [GlobalUserDependent](#globaluserdependent)
    * [Meeting](#meeting)
    * [ProjectDependent](#projectdependent)
    * [Rule](#rule)
    * [SchemaDefinition](#schemadefinition)
    * [TemplateField](#templatefield)

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalUsers</strong></td>
<td valign="top">[<a href="#globaluser">GlobalUser</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalUser</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentUser</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentImsUser</strong></td>
<td valign="top"><a href="#imsuser">ImsUser</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalUserRoles</strong></td>
<td valign="top">[<a href="#globaluserrole">GlobalUserRole</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>globalUserRole</strong></td>
<td valign="top"><a href="#globaluserrole">GlobalUserRole</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventTypes</strong></td>
<td valign="top">[<a href="#eventtype">EventType</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventType</strong></td>
<td valign="top"><a href="#eventtype">EventType</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createprojectinput">CreateProjectInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateProject</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td></td>
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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutateProject</strong></td>
<td valign="top"><a href="#projectmutation">ProjectMutation</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>register</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createglobaluserinput">CreateGlobalUserInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>joinProject</strong></td>
<td valign="top"><a href="#userinproject">UserInProject</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateGlobalUser</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td></td>
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
<td></td>
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
<td></td>
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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createGlobalUserRole</strong></td>
<td valign="top"><a href="#globaluserrole">GlobalUserRole</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createglobaluserroleinput">CreateGlobalUserRoleInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateGlobalUserRole</strong></td>
<td valign="top"><a href="#globaluserrole">GlobalUserRole</a>!</td>
<td></td>
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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createEventType</strong></td>
<td valign="top"><a href="#eventtype">EventType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createeventtypeinput">CreateEventTypeInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateEventType</strong></td>
<td valign="top"><a href="#eventtype">EventType</a>!</td>
<td></td>
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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

## Objects

### AnimalVoting

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>animalVotingStates</strong></td>
<td valign="top">[<a href="#animalvotingstate">AnimalVotingState</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finished</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>votingResult</strong></td>
<td valign="top"><a href="#animal">Animal</a></td>
<td></td>
</tr>
</tbody>
</table>

### AnimalVotingState

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

### CodeRepositorySettings

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
<td colspan="2" valign="top"><strong>codeRepositoryName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### DefaultEvent

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
<td valign="top"><a href="#eventtype">EventType</a>!</td>
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
<td valign="top"><a href="#event">Event</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventData</strong></td>
<td valign="top">[<a href="#defaulttemplatefield">DefaultTemplateField</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>children</strong></td>
<td valign="top">[<a href="#event">Event</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td></td>
</tr>
</tbody>
</table>

### DefaultEventType

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
<td valign="top"><a href="#schemadefinition">SchemaDefinition</a>!</td>
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
<td></td>
</tr>
</tbody>
</table>

### DefaultTemplateField

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
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### DefinitionOfDoneItem

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>required</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>implies</strong></td>
<td valign="top">[<a href="#definitionofdoneitem">DefinitionOfDoneItem</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### EstimationStats

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>avatar</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>roles</strong></td>
<td valign="top">[<a href="#globaluserrole">GlobalUserRole</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userInProject</strong></td>
<td valign="top"><a href="#userinproject">UserInProject</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>userInProjects</strong></td>
<td valign="top">[<a href="#userinproject">UserInProject</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### GlobalUserRole

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

### ImsSettings

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueStates</strong></td>
<td valign="top">[<a href="#issuestate">IssueState</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsProjectId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
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
<td colspan="2" valign="top"><strong>issuePriorities</strong></td>
<td valign="top">[<a href="#issuepriorityconfiguration">IssuePriorityConfiguration</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### ImsUser

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>isAdmin</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
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

Unique identifier of the scrum game the issue belongs to.

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

Detailed description of the issue. GitLab-flavored markdown is supported.

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
<td colspan="2" valign="top"><strong>sprint</strong></td>
<td valign="top"><a href="#sprint">Sprint</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>assignees</strong></td>
<td valign="top">[<a href="#userinproject">UserInProject</a>]!</td>
<td></td>
</tr>
</tbody>
</table>

### IssueEstimation

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
<td colspan="2" valign="top"><strong>issueId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeIssueTitle</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">title</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeIssueDescription</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">description</td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeIssueState</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">stateName</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeIssueType</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">typeName</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>changeSprint</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">sprintNumber</td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finishIssue</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td></td>
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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">assigneeId</td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td></td>
</tr>
</tbody>
</table>

### IssuePriorityConfiguration

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectBoard</strong></td>
<td valign="top"><a href="#projectboard">ProjectBoard</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issues</strong></td>
<td valign="top">[<a href="#issue">Issue</a>!]!</td>
<td></td>
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

### MeetingAttendee

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
<td colspan="2" valign="top"><strong>state</strong></td>
<td valign="top"><a href="#userstate">UserState</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>role</strong></td>
<td valign="top"><a href="#meetingrole">MeetingRole</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### MvelRule

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
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>triggerEventType</strong></td>
<td valign="top"><a href="#eventtype">EventType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mvelExpression</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### NameVoting

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>nameVotingStates</strong></td>
<td valign="top">[<a href="#namevotingstate">NameVotingState</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finished</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>votingResult</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
</tbody>
</table>

### NameVotingState

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

### PlanningMeeting

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentPage</strong></td>
<td valign="top"><a href="#planningmeetingpage">PlanningMeetingPage</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>animalVoting</strong></td>
<td valign="top"><a href="#animalvoting">AnimalVoting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>nameVoting</strong></td>
<td valign="top"><a href="#namevoting">NameVoting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueEstimation</strong></td>
<td valign="top"><a href="#issueestimation">IssueEstimation</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintGoalVoting</strong></td>
<td valign="top"><a href="#sprintgoalvoting">SprintGoalVoting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>meetingType</strong></td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
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

### PlanningMeetingMutation

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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">animal</td>
<td valign="top"><a href="#animal">Animal</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>endAnimalVoting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>addName</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>voteName</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>endNameVoting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>voteEstimation</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">estimation</td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>restartEstimation</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>nextIssue</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">issueId</td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>startCountdown</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">seconds</td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>endEstimation</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>setFinalResult</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">estimation</td>
<td valign="top"><a href="#tshirtsizeestimation">TShirtSizeEstimation</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>addSprintIssue</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">issueId</td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>removeSprintIssue</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">issueId</td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finishMeeting</strong></td>
<td valign="top"><a href="#sprint">Sprint</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### PlanningSettings

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
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintStartDate</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>customGoldChallengeReward</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
</tbody>
</table>

### PrivateUserInfo

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
<td colspan="2" valign="top"><strong>level</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### Project

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
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectSettings</strong></td>
<td valign="top"><a href="#projectsettings">ProjectSettings</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentSprintNumber</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentSprint</strong></td>
<td valign="top"><a href="#sprint">Sprint</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprints</strong></td>
<td valign="top">[<a href="#sprint">Sprint</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>users</strong></td>
<td valign="top">[<a href="#userinproject">UserInProject</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>currentUser</strong></td>
<td valign="top"><a href="#userinproject">UserInProject</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>activePlanningMeeting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>activeStandupMeeting</strong></td>
<td valign="top"><a href="#standupmeeting">StandupMeeting</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>activeRetrospectiveMeeting</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>roles</strong></td>
<td valign="top">[<a href="#projectrole">ProjectRole</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>role</strong></td>
<td valign="top"><a href="#projectrole">ProjectRole</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">name</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issues</strong></td>
<td valign="top">[<a href="#issue">Issue</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issue</strong></td>
<td valign="top"><a href="#issue">Issue</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectBoard</strong></td>
<td valign="top"><a href="#projectboard">ProjectBoard</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>events</strong></td>
<td valign="top">[<a href="#event">Event</a>!]!</td>
<td></td>
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
</tbody>
</table>

### ProjectBoard

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
<td colspan="2" valign="top"><strong>states</strong></td>
<td valign="top">[<a href="#issuestateinboard">IssueStateInBoard</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

### ProjectMutation

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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">type</td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>leaveMeeting</strong></td>
<td valign="top"><a href="#meeting">Meeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">type</td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>pingMeeting</strong></td>
<td valign="top"><a href="#meeting">Meeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">type</td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>promoteToMeetingLeader</strong></td>
<td valign="top"><a href="#meeting">Meeting</a>!</td>
<td></td>
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
<td colspan="2" valign="top"><strong>createPlanningMeeting</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#planningmeetinginput">PlanningMeetingInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutatePlanningMeeting</strong></td>
<td valign="top"><a href="#planningmeetingmutation">PlanningMeetingMutation</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createStandupMeeting</strong></td>
<td valign="top"><a href="#standupmeeting">StandupMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#standupmeetinginput">StandupMeetingInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutateStandupMeeting</strong></td>
<td valign="top"><a href="#standupmeetingmutation">StandupMeetingMutation</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createRetrospectiveMeeting</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#retrospectivemeetinginput">RetrospectiveMeetingInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutateRetrospectiveMeeting</strong></td>
<td valign="top"><a href="#retrospectivemeetingmutation">RetrospectiveMeetingMutation</a>!</td>
<td></td>
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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createsprintinput">CreateSprintInput</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mutateIssue</strong></td>
<td valign="top"><a href="#issuemutation">IssueMutation</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">id</td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>createIssue</strong></td>
<td valign="top"><a href="#issue">Issue</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createissueinput">CreateIssueInput</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### ProjectRole

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

### RetrospectiveMeeting

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
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
<td></td>
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
<td valign="top"><a href="#id">ID</a>!</td>
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
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>image</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### Sprint

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
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>name</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>animal</strong></td>
<td valign="top"><a href="#animal">Animal</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>number</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
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
<td colspan="2" valign="top"><strong>customGoldChallengeReward</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issues</strong></td>
<td valign="top">[<a href="#issue">Issue</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>stats</strong></td>
<td valign="top"><a href="#sprintstats">SprintStats</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### SprintGoalVoting

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
<td colspan="2" valign="top"><strong>totalStoryPoints</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>averageStoryPoints</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>percentageStoryPointsCompleted</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>percentageStoryPointsInProgress</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>percentageStoryPointsNotStarted</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueCount</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>daysLeft</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>daysElapsed</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>percentageTimeElapsed</strong></td>
<td valign="top"><a href="#float">Float</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### StandupMeeting

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
<td valign="top"><a href="#standupmeeting">StandupMeeting</a>!</td>
<td></td>
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
<td></td>
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
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
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
<td colspan="2" valign="top"><strong>privateInfo</strong></td>
<td valign="top"><a href="#privateuserinfo">PrivateUserInfo</a>!</td>
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
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#userinproject">UserInProject</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPointsCompleted</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPointsAssigned</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### Vote

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
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td></td>
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
<td colspan="2" valign="top"><strong>codeRepositoryName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### CreateEventInput

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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>timestamp</strong></td>
<td valign="top"><a href="#datetime">DateTime</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>message</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
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
<td valign="top">[<a href="#templatefieldinput">TemplateFieldInput</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>parentId</strong></td>
<td valign="top"><a href="#uuid">UUID</a></td>
<td></td>
</tr>
</tbody>
</table>

### CreateEventTypeInput

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
<td></td>
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
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>checked</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>reasonIfNotChecked</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>children</strong></td>
<td valign="top">[<a href="#definitionofdoneconfirmstate">DefinitionOfDoneConfirmState</a>!]!</td>
<td></td>
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

### EventFilter

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
<td colspan="2" valign="top"><strong>eventTypeIdentifier</strong></td>
<td valign="top"><a href="#stringfilter">StringFilter</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>minVisibility</strong></td>
<td valign="top"><a href="#eventvisibility">EventVisibility</a></td>
<td></td>
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
<td colspan="2" valign="top"><strong>since</strong></td>
<td valign="top"><a href="#datetime">DateTime</a></td>
<td></td>
</tr>
</tbody>
</table>

### FieldSchemaDefinitionInput

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

### ImsSettingsInput

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
<td colspan="2" valign="top"><strong>imsProjectId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
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
<td colspan="2" valign="top"><strong>issuePriorities</strong></td>
<td valign="top">[<a href="#issuepriorityinput">IssuePriorityInput</a>!]!</td>
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
<td colspan="2" valign="top"><strong>title</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
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
<td colspan="2" valign="top"><strong>title</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### SchemaDefinitionInput

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
<td colspan="2" valign="top"><strong>title</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
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

### TemplateFieldInput

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
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### UpdateEventTypeInput

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

Only for admins. Not visible to users.

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

### MeetingRole

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

The `Float` scalar type represents signed double-precision fractional values as specified by [IEEE 754](https://en.wikipedia.org/wiki/IEEE_floating_point).

### ID

The `ID` scalar type represents a unique identifier, often used to refetch an object or as key for a cache. The ID type appears in a JSON response as a String; however, it is not intended to be human-readable. When expected as an input type, any string (such as `"4"`) or integer (such as `4`) input value will be accepted as an ID.

### Int

The `Int` scalar type represents non-fractional signed whole numeric values. Int can represent values between -(2^31) and 2^31 - 1.

### LocalTime

### String

The `String` scalar type represents textual data, represented as UTF-8 character sequences. The String type is most often used by GraphQL to represent free-form human-readable text.

### Time

### UUID

### Url


## Interfaces


### Event

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
<td valign="top"><a href="#eventtype">EventType</a>!</td>
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
<td valign="top"><a href="#event">Event</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>eventData</strong></td>
<td valign="top">[<a href="#templatefield">TemplateField</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>children</strong></td>
<td valign="top">[<a href="#event">Event</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>user</strong></td>
<td valign="top"><a href="#globaluser">GlobalUser</a></td>
<td></td>
</tr>
</tbody>
</table>

### EventType

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
<td valign="top"><a href="#schemadefinition">SchemaDefinition</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>messageTemplate</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### FieldSchemaDefinition

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

### GlobalUserDependent

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
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### Meeting

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

### ProjectDependent

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
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### Rule

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
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>triggerEventType</strong></td>
<td valign="top"><a href="#eventtype">EventType</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### SchemaDefinition

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
<td></td>
</tr>
</tbody>
</table>

### TemplateField

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
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>
