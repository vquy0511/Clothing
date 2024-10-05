const app = angular.module("shopping-cart-app", [])
app.controller("shopping-cart-ctrl", function ($scope, $http) {

    $scope.size
    $scope.color
    $scope.cart = {
        items: [],
       
        add(id) {
            var item = this.items.find(item => item.id == id && item.size == $scope.size && item.color == $scope.color)
            if($scope.size === undefined || $scope.color === undefined){
                if($scope.size === undefined){
                    alert("Bạn chưa chọn size")
                }else{
                    alert("Bạn chưa chọn màu")
                }
            }else{
                var size = $scope.size
                var color = $scope.color
            if (item) {
                item.qty++
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    resp.data.size = size
                    resp.data.color = color
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }}
        },
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0)
        },
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, qty) => total += qty, 0)
        },
        clear() {
            this.items = [];
            localStorage.setItem('cart', []);
        },
        remove(id) {
            var index = this.items.findIndex(item => item.id == id)
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart",json);

        },
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart")
            this.items = json ? JSON.parse(json) : [];
        }
    }

    $scope.order = {
        create_date: new Date(),
        address:"",
        account: {username: $("#username").text()},
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product:{id:item.id},
                    price:item.price,
                    quantity:item.qty,
                    size:item.size,
                    color:item.color
                }
            })
        },
        purchase(){
            var order = angular.copy(this)
            $http.post("/rest/orders",order).then(resp => {
                alert("Đặt hàng thành công!");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id
            }).catch(err => {
                alert("Đặt hàng lỗi!" + err)
                console.log(err)
            })
        }
    };
    $scope.cart.loadFromLocalStorage();

})