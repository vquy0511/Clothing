USE [master]
GO
/****** Object:  Database [j6security]    Script Date: 12/2/2023 10:39:52 AM ******/
CREATE DATABASE [j6security]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'j6security', FILENAME = N'D:\j6security\j6security.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'j6security_log', FILENAME = N'D:\j6security\j6security_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [j6security] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [j6security].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [j6security] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [j6security] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [j6security] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [j6security] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [j6security] SET ARITHABORT OFF 
GO
ALTER DATABASE [j6security] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [j6security] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [j6security] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [j6security] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [j6security] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [j6security] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [j6security] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [j6security] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [j6security] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [j6security] SET  DISABLE_BROKER 
GO
ALTER DATABASE [j6security] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [j6security] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [j6security] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [j6security] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [j6security] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [j6security] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [j6security] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [j6security] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [j6security] SET  MULTI_USER 
GO
ALTER DATABASE [j6security] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [j6security] SET DB_CHAINING OFF 
GO
ALTER DATABASE [j6security] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [j6security] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [j6security] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [j6security] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [j6security] SET QUERY_STORE = OFF
GO
USE [j6security]
GO
/****** Object:  Table [dbo].[Accounts]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accounts](
	[username] [varchar](20) NOT NULL,
	[password] [nvarchar](100) NULL,
	[fullname] [nvarchar](50) NULL,
	[email] [varchar](50) NULL,
	[photo] [nvarchar](50) NULL,
	[mobile] [varchar](20) NULL,
	[addres] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Authorities]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authorities](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](20) NULL,
	[roleID] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[id] [char](4) NOT NULL,
	[name] [nvarchar](50) NULL,
	[sex] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[color_division]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[color_division](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NOT NULL,
	[color_id] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Colors]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Colors](
	[id] [varchar](10) NOT NULL,
	[color_name] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_details]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_details](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[order_id] [bigint] NULL,
	[product_id] [int] NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[size] [varchar](20) NULL,
	[color] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[address] [varchar](255) NULL,
	[username] [varchar](20) NULL,
	[create_date] [date] NOT NULL,
	[status] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
	[image] [nvarchar](255) NULL,
	[price] [float] NULL,
	[available] [bit] NULL,
	[categoryID] [char](4) NULL,
	[create_date] [date] NOT NULL,
	[sex] [bit] NULL,
	[quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Roles]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Roles](
	[id] [varchar](10) NOT NULL,
	[name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[size_division]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[size_division](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[product_id] [int] NOT NULL,
	[size_id] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sizes]    Script Date: 12/2/2023 10:39:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sizes](
	[id] [varchar](10) NOT NULL,
	[size_name] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Accounts] ([username], [password], [fullname], [email], [photo], [mobile], [addres]) VALUES (N'user', N'123', N'Thoi', N'korea1@gmail.com', N'IMG_0019.JPG', N'', NULL)
INSERT [dbo].[Accounts] ([username], [password], [fullname], [email], [photo], [mobile], [addres]) VALUES (N'user1', N'123', N'Thoi', N'korea1@gmail.com', N'IMG_0019.JPG', N'123', NULL)
INSERT [dbo].[Accounts] ([username], [password], [fullname], [email], [photo], [mobile], [addres]) VALUES (N'user12', N'123', N'Quốc 12', N'korea12@gmail.com', N'IMG_0019.JPG', N'123', NULL)
INSERT [dbo].[Accounts] ([username], [password], [fullname], [email], [photo], [mobile], [addres]) VALUES (N'user123', N'123', N'123', N'koreann2003@gmail.com', N'unnamed.png', N'123', NULL)
INSERT [dbo].[Accounts] ([username], [password], [fullname], [email], [photo], [mobile], [addres]) VALUES (N'user23', N'123', N'123', N'koreann2003@gmail.com', N'IMG_0019.JPG', N'123', NULL)
INSERT [dbo].[Accounts] ([username], [password], [fullname], [email], [photo], [mobile], [addres]) VALUES (N'user4', N'123', N'142', N'korea@gmail.com', NULL, N'0399214849', N'Gò Vấp')
GO
SET IDENTITY_INSERT [dbo].[Authorities] ON 

INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (5, N'user', N'DIRE')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (6, N'user1', N'STAF')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (7, N'user4', N'CUST')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (8, N'user4', N'CUST')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (9, N'user4', N'CUST')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (10, N'user', N'STAF')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (11, N'user12', N'CUST')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (12, N'user123', N'CUST')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (13, N'user23', N'CUST')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (14, N'user', N'CUST')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (15, N'user12', N'STAF')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (16, N'user123', N'CUST')
INSERT [dbo].[Authorities] ([id], [username], [roleID]) VALUES (17, N'user1', N'DIRE')
SET IDENTITY_INSERT [dbo].[Authorities] OFF
GO
INSERT [dbo].[Categories] ([id], [name], [sex]) VALUES (N'AO  ', N'Áo 1234', 1)
INSERT [dbo].[Categories] ([id], [name], [sex]) VALUES (N'GIAY', N'Giày 1', 1)
INSERT [dbo].[Categories] ([id], [name], [sex]) VALUES (N'QUAN', N'Quần', 1)
GO
SET IDENTITY_INSERT [dbo].[color_division] ON 

INSERT [dbo].[color_division] ([id], [product_id], [color_id]) VALUES (1, 4, N'BLA')
INSERT [dbo].[color_division] ([id], [product_id], [color_id]) VALUES (2, 4, N'BLU')
INSERT [dbo].[color_division] ([id], [product_id], [color_id]) VALUES (3, 3, N'BLA')
INSERT [dbo].[color_division] ([id], [product_id], [color_id]) VALUES (4, 3, N'WHI')
INSERT [dbo].[color_division] ([id], [product_id], [color_id]) VALUES (5, 2, N'BLA')
INSERT [dbo].[color_division] ([id], [product_id], [color_id]) VALUES (6, 2, N'WHI')
INSERT [dbo].[color_division] ([id], [product_id], [color_id]) VALUES (7, 1, N'BLA')
INSERT [dbo].[color_division] ([id], [product_id], [color_id]) VALUES (8, 1, N'WHI')
SET IDENTITY_INSERT [dbo].[color_division] OFF
GO
INSERT [dbo].[Colors] ([id], [color_name]) VALUES (N'BLA', N'Đen')
INSERT [dbo].[Colors] ([id], [color_name]) VALUES (N'BLU', N'Xanh')
INSERT [dbo].[Colors] ([id], [color_name]) VALUES (N'WHI', N'Trắng')
GO
SET IDENTITY_INSERT [dbo].[order_details] ON 

INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (39, 44, 2, 2000, 1, N'S', N'Đen')
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (40, 44, 3, 3000, 1, N'M', N'Đen')
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (41, 45, 2, 2000, 1, N'L', N'Trắng')
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (42, 46, 1, 1000, 1, N'L', N'Trắng')
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (43, 46, 4, 4000, 2, N'S', N'Đen')
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (44, 47, 2, 2000, 1, N'S', N'Đen')
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (45, 48, 1, 1000, 1, N'S', N'Đen')
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (46, 49, 2, 2000, 1, N'S', N'Đen')
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (47, 50, 2, 2000, 1, N'S', N'Đen')
INSERT [dbo].[order_details] ([id], [order_id], [product_id], [price], [quantity], [size], [color]) VALUES (48, 51, 2, 2000, 1, N'S', N'Đen')
SET IDENTITY_INSERT [dbo].[order_details] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([id], [address], [username], [create_date], [status]) VALUES (44, N'1', N'user', CAST(N'2023-08-09' AS Date), N'Thành công')
INSERT [dbo].[Orders] ([id], [address], [username], [create_date], [status]) VALUES (45, N'2', N'user', CAST(N'2023-08-09' AS Date), N'Thành công')
INSERT [dbo].[Orders] ([id], [address], [username], [create_date], [status]) VALUES (46, N'123', N'user1', CAST(N'2023-08-10' AS Date), N'Thành công')
INSERT [dbo].[Orders] ([id], [address], [username], [create_date], [status]) VALUES (47, N'dwdewd', N'user123', CAST(N'2023-11-27' AS Date), N'Thành công')
INSERT [dbo].[Orders] ([id], [address], [username], [create_date], [status]) VALUES (48, N'?eferfe', N'user123', CAST(N'2023-11-27' AS Date), N'Chờ xác nhận')
INSERT [dbo].[Orders] ([id], [address], [username], [create_date], [status]) VALUES (49, N'ferfer', N'user123', CAST(N'2023-11-27' AS Date), N'Chờ xác nhận')
INSERT [dbo].[Orders] ([id], [address], [username], [create_date], [status]) VALUES (50, N'de', N'user123', CAST(N'2023-11-27' AS Date), N'Chờ xác nhận')
INSERT [dbo].[Orders] ([id], [address], [username], [create_date], [status]) VALUES (51, N'', N'user', CAST(N'2023-12-01' AS Date), N'Chờ xác nhận')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([id], [name], [image], [price], [available], [categoryID], [create_date], [sex], [quantity]) VALUES (1, N'Áo polo', N'ao.webp', 1000, 1, N'GIAY', CAST(N'2023-12-01' AS Date), 0, 98)
INSERT [dbo].[Products] ([id], [name], [image], [price], [available], [categoryID], [create_date], [sex], [quantity]) VALUES (2, N'Áo thun đen', N'123.webp', 2000, 1, N'AO  ', CAST(N'2023-12-01' AS Date), 1, 91)
INSERT [dbo].[Products] ([id], [name], [image], [price], [available], [categoryID], [create_date], [sex], [quantity]) VALUES (3, N'Áo mixi', N'mixi.jpg', 3000, 1, N'AO  ', CAST(N'2023-07-30' AS Date), 0, 84)
INSERT [dbo].[Products] ([id], [name], [image], [price], [available], [categoryID], [create_date], [sex], [quantity]) VALUES (4, N'Quần jean xuông', N'quan1.jpg', 4000, 1, N'QUAN', CAST(N'2023-07-30' AS Date), 1, 91)
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
INSERT [dbo].[Roles] ([id], [name]) VALUES (N'CUST', N'customer')
INSERT [dbo].[Roles] ([id], [name]) VALUES (N'DIRE', N'director')
INSERT [dbo].[Roles] ([id], [name]) VALUES (N'STAF', N'staff')
GO
SET IDENTITY_INSERT [dbo].[size_division] ON 

INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (1, 1, N'S')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (2, 1, N'M')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (3, 1, N'L')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (4, 3, N'S')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (5, 3, N'M')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (6, 3, N'L')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (7, 2, N'S')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (8, 2, N'M')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (9, 2, N'L')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (10, 4, N'S')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (11, 4, N'M')
INSERT [dbo].[size_division] ([id], [product_id], [size_id]) VALUES (12, 4, N'L')
SET IDENTITY_INSERT [dbo].[size_division] OFF
GO
INSERT [dbo].[Sizes] ([id], [size_name]) VALUES (N'L', N'Lớn')
INSERT [dbo].[Sizes] ([id], [size_name]) VALUES (N'M', N'Vừa')
INSERT [dbo].[Sizes] ([id], [size_name]) VALUES (N'S', N'Nhỏ')
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Accounts] ([username])
GO
ALTER TABLE [dbo].[Authorities]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[Roles] ([id])
GO
ALTER TABLE [dbo].[color_division]  WITH CHECK ADD FOREIGN KEY([color_id])
REFERENCES [dbo].[Colors] ([id])
GO
ALTER TABLE [dbo].[color_division]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([id])
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([order_id])
REFERENCES [dbo].[Orders] ([id])
GO
ALTER TABLE [dbo].[order_details]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([id])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Accounts] ([username])
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD FOREIGN KEY([categoryID])
REFERENCES [dbo].[Categories] ([id])
GO
ALTER TABLE [dbo].[size_division]  WITH CHECK ADD FOREIGN KEY([product_id])
REFERENCES [dbo].[Products] ([id])
GO
ALTER TABLE [dbo].[size_division]  WITH CHECK ADD FOREIGN KEY([size_id])
REFERENCES [dbo].[Sizes] ([id])
GO
USE [master]
GO
ALTER DATABASE [j6security] SET  READ_WRITE 
GO
