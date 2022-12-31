output "rds_endpoint" {
  description = "Endpoint to RDS database"
  value       = aws_db_instance.default.endpoint
}
