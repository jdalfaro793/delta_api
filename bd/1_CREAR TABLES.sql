CREATE TABLE [users]
(
[id] INT PRIMARY KEY IDENTITY (1, 1),
[username] [varchar](250) NOT NULL,
[email] [varchar](100) NOT NULL,
[password] [varchar](255) NOT NULL,
[status] [int] NOT NULL,
[registration] [datetime2](0) NOT NULL,
[modification] [datetime2](0) NOT NULL,
)



CREATE TABLE [roles]
(
[id] INT PRIMARY KEY IDENTITY (1, 1),
[name] [varchar](100) NOT NULL,
[description] [varchar](100) NULL,
[status] [int] NOT NULL,
[registration] [datetime2](0) NOT NULL,
[modification] [datetime2](0) NOT NULL,
)


CREATE TABLE [dbo].[user_roles]
(
    [user_id] INT NOT NULL,          -- Clave foránea hacia la tabla users
    [role_id] INT NOT NULL,          -- Clave foránea hacia la tabla roles
    [status] INT NOT NULL,			 -- Estado de la relación (1 = activo, 0 = inactivo)
    [registration] DATETIME2(0) NOT NULL, -- Fecha de asignación del rol
	[modification] DATETIME2(0) NOT NULL, -- Fecha de asignación del rol
    CONSTRAINT PK_user_role PRIMARY KEY (user_id, role_id), -- Clave primaria compuesta
    CONSTRAINT FK_user_role_user FOREIGN KEY (user_id) REFERENCES [dbo].[users]([id]), -- Relación con users
    CONSTRAINT FK_user_role_role FOREIGN KEY (role_id) REFERENCES [dbo].[roles]([id]) -- Relación con roles
);