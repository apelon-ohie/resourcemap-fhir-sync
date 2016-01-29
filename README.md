# ResourceMap-FHIR Sync

This is a tool to populate the Resource Map Layer with SCT Facility Codes from FHIR. This project needs to be expanded to send JSON commands (Containing FHIR Data) to any system. But it was originally developed to simply populate the Resource Map Facility Types with SCT Codes


## Usage
To populate Resource Map with FHIR Facility-Type Data, simply instantiate the class, then send the  desired command to Resource Map's API Endpoint.
```
rmRestScript RmapManager = new rmRestScript();
```

Next, get the JSON Command - to update Resource Map Facility-Type layer - that contains the SCT Facilities
```
String updateLayerCommand = - main.getJsonCommand();
```

Finally, execute the command by sending the JSON data to the Resource Map Server
```
main.executeCommand(updateLayerCommand);
```

## Tasks
 - Make Getters and Setters for FHIR Server URL, Username and Password
 - Break-out the  FHIR Server connection, allowing a user to build a (pre-defined) JSON command by passing-in the FHIR Value Set 
