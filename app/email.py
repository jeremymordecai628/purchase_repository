#!/usr/bin/python3

import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText

# SMTP server configuration
smtp_server = "smtp.example.com"
smtp_port = 587
smtp_user = "your_email@example.com"
smtp_password = "your_password"

# Email content
subject = "Test Email"
body = "This is a test email sent to multiple recipients."

# List of recipients
recipients = ["recipient1@example.com", "recipient2@example.com", "recipient3@example.com"]

# Create the email
msg = MIMEMultipart()
msg['From'] = smtp_user
msg['To'] = ", ".join(recipients)  # Use comma-separated string of recipients
msg['Subject'] = subject
msg.attach(MIMEText(body, 'plain'))

# Send the email
try:
    server = smtplib.SMTP(smtp_server, smtp_port)
    server.starttls()
    server.login(smtp_user, smtp_password)
    server.sendmail(smtp_user, recipients, msg.as_string())
    server.quit()
    print("Emails sent successfully!")
except Exception as e:
    print(f"Failed to send email: {e}")
