# Consul and Keycloak Setup Documentation

This repository provides documentation on how to set up Consul and Keycloak, including creating realms, clients, users, roles, and updating Consul's configuration file.

## Table of Contents

- [Consul Setup](#consul-setup)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Update Configurations](#update-configurations)
  - [Start Consul](#start-consul)
- [Keycloak Setup](#keycloak-setup)
  - [Installation](#installation-1)
  - [Access Keycloak Admin Console](#access-keycloak-admin-console)
  - [Create a Realm](#create-a-realm)
  - [Create a Client](#create-a-client)
  - [Create Roles](#create-roles)
  - [Create Users](#create-users)
  - [Assign Roles to Users](#assign-roles-to-users)

# Consul and Keycloak Setup Documentation

This repository provides documentation on how to set up Consul and Keycloak, including creating realms, clients, users, roles, and updating Consul's configuration file.

## Table of Contents

- [Consul Setup](#consul-setup)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Update Configurations](#update-configurations)
  - [Start Consul](#start-consul)
- [Keycloak Setup](#keycloak-setup)
  - [Installation](#installation-1)
  - [Access Keycloak Admin Console](#access-keycloak-admin-console)
  - [Create a Realm](#create-a-realm)
  - [Create a Client](#create-a-client)
  - [Create Roles](#create-roles)
  - [Create Users](#create-users)
  - [Assign Roles to Users](#assign-roles-to-users)

## Consul Setup

### Installation

1. Download and install Consul on your desired platform by following the official installation guide.

### Configuration

1. Locate the `config.json` file in your Consul installation directory.
2. Open the `config.json` file using a text editor.
3. If not found create one file with `config.json` in your Consul installation directory.

### Update Configurations

1. Replace the content of the `config.json` file with the following JSON configuration:

```json
{
  "datacenter": "dc1",
  "data_dir": "<path_to_data_directory>",
  "bind_addr": "127.0.0.1",
  "client_addr": "0.0.0.0",
  "bootstrap_expect": 1,
  "server": true,
  "ui": true
}
```

### Start Consul

1. Launch Consul by executing the appropriate command for your platform.
   - Example (Linux/macOS): `consul agent -config-file=<path_to_config.json>`.
2. Ensure that Consul starts successfully and note down the server address and port for further use.

## Keycloak Setup

### Installation

1. Download and install Keycloak by following the official installation guide for your operating system.

### Run keyCloak

1. Open command prompt in your keycloak<version> folder directory.
2. By using command start the keycloak in port 9080
   `bin\kc.bat start-dev --http-port=9080 `.

### Access Keycloak Admin Console

1. Launch Keycloak and access the Keycloak Admin Console using your preferred web browser.
   - Default URL: `http://localhost:9080/auth/admin`.

### Create a Realm

1. Log in to the Keycloak Admin Console with the default credentials or your configured admin account.
2. Click on the "Master" realm at the top-left corner and select "Add realm".
3. Provide a name for your new realm, and click "Create" `axelra`.

### Create a Client

1. Inside your newly created realm, go to the "Clients" section in the left-hand menu.
2. Click on the "Create" button to add a new client `axelra-mfec`.
3. Fill in the required details like Client ID, Client Protocol, and Root URL.
4. Save the changes.

### Create Roles

1.  Navigate to the "Roles" section in the left-hand menu.
2.  Click on the "Add Role" button to create a new role.

        ROLE_ADMIN
        ROLE_USER

3.  Save each role created.

### Create Users

1.  Go to the "Users" section in the left-hand menu.
2.  Click on the "Add User" button to create a new user.
3.  Fill in the user details such as username, email, and password.
4.  Save the changes.

        admin
        user

### Assign Roles to Users

1.  Select a user from the list and go to the "Role Mappings" tab.
2.  Move the desired roles from the "Available Roles" to the "Assigned Roles" section.
3.  Click "Save" to assign the roles to the user.

        admin - assign role ROLE_ADMIN
        user - assign role ROLE_USER

### Create Groups

1.  Go to "Groups" section in the left-hand menu.
2.  Click on "Create Group" button to create a new group.
3.  Save each group created.
        1. Admins
        2. Users

### Assign Groups to Users

1.  Select a group from the list and click on add member button.
2.  Select desired user to add into group
3.  Click on "Add button" to save.

        Admins - add admin
        Users - add user

### Update client-details

1.  Open clients from left-hand menu.
2.  Select `axelra-mfec` from the list of clients.
3.  In the client-details update fields as shown below.

        Client ID : axelra-mfec

        Name : axelra-mfec

        Description : axelra-mfec

        Root URL : http://localhost:8080

        Home URL : http://localhost:8080

        Valid redirect URLs : http://localhost:8081/*
                            : http://localhost:8080/*


        Valid post logout redirect URIs : http://localhost:8080/*
        Web Origins : http://localhost:8080/*

        Admin URL : http://localhost:8080

4.  Click on "save" button to update the client-details.
