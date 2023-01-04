terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.16"
    }
  }

  required_version = ">= 1.2.0"
}

provider "aws" {
  region  = "us-east-1"
}

resource "aws_db_instance" "default" {
  identifier            = "rds-database"
  allocated_storage     = 20
  db_name               = var.database_name
  engine                = "postgres"
  engine_version        = "14"
  instance_class        = "db.t3.micro"
  username              = var.database_username
  password              = var.database_password
  publicly_accessible   = true
  skip_final_snapshot   = true
}
