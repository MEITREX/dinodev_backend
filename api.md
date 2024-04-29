# Scrum Game API

<details>
  <summary><strong>Table of Contents</strong></summary>

* [Query](#query)
* [Mutation](#mutation)
* [Objects](#objects)
    * [AnimalVoting](#animalvoting)
    * [CodeRepositorySettings](#coderepositorysettings)
    * [GlobalUser](#globaluser)
    * [GlobalUserRole](#globaluserrole)
    * [GropiusIssueStateMapping](#gropiusissuestatemapping)
    * [GropiusIssueTypeMapping](#gropiusissuetypemapping)
    * [GropiusSettings](#gropiussettings)
    * [ImsSettings](#imssettings)
    * [Issue](#issue)
    * [IssueEstimation](#issueestimation)
    * [IssueEstimations](#issueestimations)
    * [IssueMutation](#issuemutation)
    * [IssueState](#issuestate)
    * [IssueType](#issuetype)
    * [MeetingAttendee](#meetingattendee)
    * [NameVoting](#namevoting)
    * [PaginationInfo](#paginationinfo)
    * [PlanningMeeting](#planningmeeting)
    * [PlanningMeetingMutation](#planningmeetingmutation)
    * [PlanningSettings](#planningsettings)
    * [PrivateUserInfo](#privateuserinfo)
    * [Project](#project)
    * [ProjectBoard](#projectboard)
    * [ProjectMutation](#projectmutation)
    * [ProjectSettings](#projectsettings)
    * [RetrospectiveMeeting](#retrospectivemeeting)
    * [ShopItem](#shopitem)
    * [Sprint](#sprint)
    * [SprintGoalVoting](#sprintgoalvoting)
    * [StandupMeeting](#standupmeeting)
    * [StoryPointVote](#storypointvote)
    * [Subscription](#subscription)
    * [UserInProject](#userinproject)
    * [UserRoleInProject](#userroleinproject)
    * [UserStats](#userstats)
    * [Vote](#vote)
    * [VotingState](#votingstate)
* [Inputs](#inputs)
    * [CodeRepositorySettingsInput](#coderepositorysettingsinput)
    * [CreateGlobalUserInput](#createglobaluserinput)
    * [CreateGlobalUserRoleInput](#createglobaluserroleinput)
    * [CreateIssueInput](#createissueinput)
    * [CreateProjectInput](#createprojectinput)
    * [CreateProjectRoleInput](#createprojectroleinput)
    * [CreateSprintInput](#createsprintinput)
    * [DateTimeFilter](#datetimefilter)
    * [ImsSettingsInput](#imssettingsinput)
    * [IntFilter](#intfilter)
    * [IssueStateInput](#issuestateinput)
    * [Pagination](#pagination)
    * [PlanningMeetingInput](#planningmeetinginput)
    * [PlanningSettingsInput](#planningsettingsinput)
    * [ProjectSettingsInput](#projectsettingsinput)
    * [StringFilter](#stringfilter)
    * [UpdateGlobalUserInput](#updateglobaluserinput)
    * [UpdateGlobalUserRoleInput](#updateglobaluserroleinput)
    * [UpdateProjectInput](#updateprojectinput)
    * [UpdateProjectRoleInput](#updateprojectroleinput)
* [Enums](#enums)
    * [GlobalPrivilege](#globalprivilege)
    * [IssueStateType](#issuestatetype)
    * [MeetingRole](#meetingrole)
    * [MeetingState](#meetingstate)
    * [MeetingType](#meetingtype)
    * [PlanningMeetingPage](#planningmeetingpage)
    * [ProjectPrivilege](#projectprivilege)
    * [SortDirection](#sortdirection)
    * [StoryPoints](#storypoints)
    * [UserState](#userstate)
* [Scalars](#scalars)
    * [Boolean](#boolean)
    * [Date](#date)
    * [DateTime](#datetime)
    * [ID](#id)
    * [Int](#int)
    * [LocalTime](#localtime)
    * [String](#string)
    * [Time](#time)
    * [UUID](#uuid)
    * [Url](#url)
* [Interfaces](#interfaces)
    * [GlobalUserDependent](#globaluserdependent)
    * [Meeting](#meeting)
    * [ProjectDependent](#projectdependent)

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
<td valign="top">[<a href="#string">String</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>animalVotingStates</strong></td>
<td valign="top">[<a href="#votingstate">VotingState</a>!]!</td>
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

### GropiusIssueStateMapping

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
<td colspan="2" valign="top"><strong>gropiusStateId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueStateName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### GropiusIssueTypeMapping

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
<td colspan="2" valign="top"><strong>gropiusTypeId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueTypeName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
</tbody>
</table>

### GropiusSettings

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
<td colspan="2" valign="top"><strong>issueTypeMappings</strong></td>
<td valign="top">[<a href="#gropiusissuetypemapping">GropiusIssueTypeMapping</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueStateMappings</strong></td>
<td valign="top">[<a href="#gropiusissuestatemapping">GropiusIssueStateMapping</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>gropiusProjectId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>gropiusUrl</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
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
</tbody>
</table>

### Issue

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
<td colspan="2" valign="top"><strong>title</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>state</strong></td>
<td valign="top"><a href="#issuestate">IssueState</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#issuetype">IssueType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintNumber</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprint</strong></td>
<td valign="top"><a href="#sprint">Sprint</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>assigneeIds</strong></td>
<td valign="top">[<a href="#uuid">UUID</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>assignees</strong></td>
<td valign="top">[<a href="#globaluser">GlobalUser</a>!]!</td>
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
<td colspan="2" valign="top"><strong>votes</strong></td>
<td valign="top">[<a href="#storypointvote">StoryPointVote</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finished</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>skipped</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>median</strong></td>
<td valign="top"><a href="#storypoints">StoryPoints</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>max</strong></td>
<td valign="top"><a href="#storypoints">StoryPoints</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>min</strong></td>
<td valign="top"><a href="#storypoints">StoryPoints</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>mode</strong></td>
<td valign="top"><a href="#storypoints">StoryPoints</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finalResult</strong></td>
<td valign="top"><a href="#storypoints">StoryPoints</a></td>
<td></td>
</tr>
</tbody>
</table>

### IssueEstimations

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
<td colspan="2" valign="top"><strong>currentIssueEstimation</strong></td>
<td valign="top"><a href="#issueestimation">IssueEstimation</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issueEstimations</strong></td>
<td valign="top">[<a href="#issueestimation">IssueEstimation</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finished</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>countdown</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
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
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
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

### IssueState

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
<td valign="top"><a href="#issuestatetype">IssueStateType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>imsStateId</strong></td>
<td valign="top"><a href="#id">ID</a>!</td>
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
<td colspan="2" valign="top"><strong>description</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>iconPath</strong></td>
<td valign="top"><a href="#string">String</a></td>
<td></td>
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
<td valign="top">[<a href="#votingstate">VotingState</a>!]!</td>
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
<td colspan="2" valign="top"><strong>nextPageAllowed</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
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
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>state</strong></td>
<td valign="top"><a href="#meetingstate">MeetingState</a>!</td>
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
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
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
<td colspan="2" valign="top"><strong>nextPage</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>voteAnimal</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">animal</td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">userId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
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
<td colspan="2" align="right" valign="top">userId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>voteStoryPoints</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">storyPoints</td>
<td valign="top"><a href="#storypoints">StoryPoints</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">userId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>skipIssue</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>startCountdown</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>setFinalResult</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">storyPoints</td>
<td valign="top"><a href="#storypoints">StoryPoints</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>addSprintIssue</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">issueId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>removeSprintIssue</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">issueId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>setStoryPointLimit</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">limit</td>
<td valign="top"><a href="#int">Int</a>!</td>
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
<td valign="top">[<a href="#userroleinproject">UserRoleInProject</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>role</strong></td>
<td valign="top"><a href="#userroleinproject">UserRoleInProject</a></td>
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
<td colspan="2" valign="top"><strong>projectId</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>states</strong></td>
<td valign="top">[<a href="#issuestate">IssueState</a>!]!</td>
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
<td colspan="2" valign="top"><strong>createRole</strong></td>
<td valign="top"><a href="#userroleinproject">UserRoleInProject</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">input</td>
<td valign="top"><a href="#createprojectroleinput">CreateProjectRoleInput</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>updateRole</strong></td>
<td valign="top"><a href="#userroleinproject">UserRoleInProject</a>!</td>
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
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>state</strong></td>
<td valign="top"><a href="#meetingstate">MeetingState</a>!</td>
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
<td colspan="2" valign="top"><strong>id</strong></td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>number</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
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
<td colspan="2" valign="top"><strong>active</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
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
<td colspan="2" valign="top"><strong>sprintIssueIds</strong></td>
<td valign="top">[<a href="#uuid">UUID</a>!]!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPointLimit</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>capacity</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>finished</strong></td>
<td valign="top"><a href="#boolean">Boolean</a>!</td>
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
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>state</strong></td>
<td valign="top"><a href="#meetingstate">MeetingState</a>!</td>
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

### StoryPointVote

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
<tr>
<td colspan="2" valign="top"><strong>storyPoints</strong></td>
<td valign="top"><a href="#storypoints">StoryPoints</a>!</td>
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
<td colspan="2" valign="top"><strong>meetingStarted</strong></td>
<td valign="top"><a href="#meeting">Meeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>meetingFinished</strong></td>
<td valign="top"><a href="#meeting">Meeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>meetingAttendeesChanged</strong></td>
<td valign="top">[<a href="#meetingattendee">MeetingAttendee</a>!]!</td>
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
<td colspan="2" valign="top"><strong>planningMeetingUpdated</strong></td>
<td valign="top"><a href="#planningmeeting">PlanningMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>standupMeetingUpdated</strong></td>
<td valign="top"><a href="#standupmeeting">StandupMeeting</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" align="right" valign="top">projectId</td>
<td valign="top"><a href="#uuid">UUID</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>retrospectiveMeetingUpdated</strong></td>
<td valign="top"><a href="#retrospectivemeeting">RetrospectiveMeeting</a>!</td>
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
<td valign="top">[<a href="#userroleinproject">UserRoleInProject</a>!]!</td>
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

### UserRoleInProject

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
<td valign="top"><a href="#globaluser">GlobalUser</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>project</strong></td>
<td valign="top"><a href="#project">Project</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>commits</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issuesCreated</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>issuesClosed</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>storyPoints</strong></td>
<td valign="top"><a href="#int">Int</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>hoursWorked</strong></td>
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

### VotingState

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
<td valign="top"><a href="#string">String</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>stateName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>typeName</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>sprintNumber</strong></td>
<td valign="top"><a href="#int">Int</a></td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>assigneeIds</strong></td>
<td valign="top">[<a href="#uuid">UUID</a>!]!</td>
<td></td>
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
<td colspan="2" valign="top"><strong>privileges</strong></td>
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
<td colspan="2" valign="top"><strong>privileges</strong></td>
<td valign="top">[<a href="#projectprivilege">ProjectPrivilege</a>!]!</td>
<td></td>
</tr>
</tbody>
</table>

## Enums

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

### IssueStateType

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

### MeetingState

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>PLANNED</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>ACTIVE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>FINISHED</strong></td>
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

### StoryPoints

<table>
<thead>
<th align="left">Value</th>
<th align="left">Description</th>
</thead>
<tbody>
<tr>
<td valign="top"><strong>NO_VOTE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>ZERO</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>ONE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>TWO</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>THREE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>FIVE</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>EIGHT</strong></td>
<td></td>
</tr>
<tr>
<td valign="top"><strong>THIRTEEN</strong></td>
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
<td colspan="2" valign="top"><strong>title</strong></td>
<td valign="top"><a href="#string">String</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>type</strong></td>
<td valign="top"><a href="#meetingtype">MeetingType</a>!</td>
<td></td>
</tr>
<tr>
<td colspan="2" valign="top"><strong>state</strong></td>
<td valign="top"><a href="#meetingstate">MeetingState</a>!</td>
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
