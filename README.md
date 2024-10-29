KYC Java Spring Application
This application leverages the GroupValidation repository to manage database field naming, validation, and translations. Here’s a quick overview:

GroupValidation Repository: GroupValidation is used for database field naming, validation, and translations.

Purpose
This program serves as the backend application for managing Know Your Customer (KYC) processes.

Key Features:
Field Naming Guidance:

At startup, objects in the dto.dto folder fetch field naming instructions from GroupValidation.

Validation Annotations:
Controllers use the @Validation annotation to validate imported field data.
Validation is executed using the GroupValidation repository API.

Frontend Application:
The frontend code can be found at: Next.js KYC Frontend.
