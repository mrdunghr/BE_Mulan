# POSTMAN

POST Đăng ký

http://localhost:8080/api/v1/customers/register

```json
{
    "username": "mrdunghr",
    "password": "123123123",
    "email": "mrdunghr@gmail.com",
    "phone": "0382564626"
}
```

GET xem danh sách key đã thuê

http://localhost:8080/api/v1/customers/key-gen?idCustomer=1


POST đăng nhập jwt

http://localhost:8080/api/v1/customers/login

```json
{
    "username": "mrdunghr",
    "password": "123123123"
}
```