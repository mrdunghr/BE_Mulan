# POSTMAN
http://localhost:8080/api/v1/customers/key-gen?idCustomer=1

POST tạo key theo khách hàng

````json
{
  //Không cần gửi gì đi ở đây
}
````

GET kiểm tra key hết hạn chưa?

http://localhost:8080/api/v1/customers/key-expiration-date?idKey=1

GET kiểm tra quyền sử dụng key - tránh 2 máy đều sử dụng key

http://localhost:8080/api/v1/customers/permission-key?idKey=1