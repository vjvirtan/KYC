<h1>Know Your Customer Application</h1>

<h2>Purpose</h2>
<p>This program serves as the backend application for managing Know Your Customer (KYC) processes.</p>
<p>This application leverages the GroupValidation repository to manage database field naming, validation, and translations. </p>

<h2>Usage</h2>
<li>You have to run GroupValidation App at the same time. </li>
<li>Front to this app from my next_kyc repo.</li>

<h2>Important</h2>
<p>If you add more dao objects to this. Then all daos have to have counterpart dto record object. You have to plase them to the /dto/dto folder. There is automation to fetch all validation, translation rules to that specific field.<h4>All field names have to have FieldDictionary entry in GroupValidation app. GroupValidation app point is standardise field naming for all apps witch use it.</h4></p>
<li>You can do custom validation rule for the field. Just go to dto record and add custom rule to it. Add -- public static final ArrayList<ValuePair<String, ValidationRuleDto>> customRules ... --. Remember to name it customRules. That is important, because FieldDictionaryService.java go /dto/dto records and check if record contains customRules field.</li>


<h2>Validation</h2>
<p>Controllers use the @Validation annotation to validate imported field data.
Validation is executed using the GroupValidation repository API.
Front end kyc--next app fetches validation, translation rules from this app. 
</p>


