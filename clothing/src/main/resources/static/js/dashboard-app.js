const app = angular.module('dashboard-app', [])
app.controller('dashboard-ctrl', function ($scope, $http, $location, $window) {

    // $routeProvider
    // 	.when('/list', {
    // 		templateUrl: '/admin/list/product.html',
    // 		controller: 'dashboard-ctrl',
    // 	})
    // 	.when('/add/:id', {
    // 		templateUrl: '/admin/manage/product.html',
    // 		controller: 'dashboard-ctrl',
    // 	})

    $scope.items = [];
    $scope.form = {};
    $scope.cates = [];
    $scope.status

    $scope.order = {
        items: [],
        loadOrder() {
            $http.get("/rest/orders").then(resp => {
                this.items = resp.data
            })
        },
        delete(item) {
            $http.delete(`/rest/orders/${item.id}`).then(resp => {
                var index = $scope.items.findIndex(p => p.id == item.id);
                this.items.splice(index, 1);
                $scope.order.loadOrder();
            }).catch(err => {
                alert("err: " + err)
            })
        },
        orderDetails: [],
        view(item) {
            $http.get(`/rest/orderDetails/${item.id}`).then(resp => {
                this.orderDetails = resp.data;
            })
        },
        changeStatus(item, status) {
            item.status = status
            console.log(status)
            $http.put(`/rest/orders/${item.id}`, item).then(resp => {
                $scope.order.loadOrder();
            })
        }
    };
    $scope.order.loadOrder();
    $scope.initialize = function () {
        // Load sản phẩm
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });
        //Load category
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        });
    }

    $scope.initialize();

    // Xóa form
    $scope.reset = function () {
        $scope.form = {
            create_date: new Date(),
            image: '',
            available: true
        }
    }

    // Hiển thị lên form
    $scope.edit = function (item) {
        var url = `http://localhost:8080/admin/product/update/${item.id}`;
        $window.location.href = url;
    }
   
    
	// ---------
	// Category function
	
	$scope.categories = [];
	$scope.category = {};
	$scope.editing = false;

	function fetchCategories() {
        $http.get('/rest/categories').then(function(response) {
            $scope.categories = response.data;
        });
    }
    
    fetchCategories();
    
    $scope.addCategory = function() {
        $http.post('/rest/categories', $scope.category).then(function(response) {
            $scope.categories.push(response.data);
            $scope.resetForm();
        });
    };
    
    $scope.resetForm = function() {
        $scope.category = {};
        $scope.editing = false;

    };
    
    $scope.updateCategory = function() {
        $http.put('/rest/categories/' + $scope.category.id, $scope.category).then(function(response) {
            console.log('Danh mục đã được cập nhật:', response.data);
			alert('Danh mục đã được cập nhật:', response.data)
            var updatedCategory = response.data;
            var index = $scope.categories.findIndex(function(c) {
                return c.id === updatedCategory.id;
            });
            if (index !== -1) {
                $scope.categories[index] = updatedCategory;
            }
            // ...
        }, function(error) {
            console.error('Lỗi khi cập nhật danh mục:', error);
        });
    };

    $scope.editCategory = function(category) {
        $scope.editing = true;
        $scope.category = angular.copy(category);
    };

   $scope.deleteCategory = function(category) {
        var confirmDelete = confirm('Bạn có chắc muốn xóa danh mục này?');
        if (confirmDelete) {
            $http.delete('/rest/categories/' + category.id).then(function(response) {
                console.log('Danh mục đã được xóa:', category);
                fetchCategories();
                 $scope.resetForm();
            }, function(error) {
                console.error('Lỗi khi xóa danh mục:', error);
            });
        }
	};


      // Authority function

      $scope.roles = [];
      $scope.admins = [];
      $scope.authorities = [];
  
      $scope.authInitialize = function () {
          $http.get("/rest/roles").then(response => {
              $scope.roles = response.data;
          })
          
          $http.get("/rest/accounts").then(response => {
              $scope.admins = response.data;
          })
  
          $http.get("/rest/authorities").then(response => {
              $scope.authorities = response.data;
          }).catch(error => {
  
          })
  
          $scope.authority_of = function (acc, role) {
              if ($scope.authorities) {
                  return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
              }
          }
  
          $scope.authority_changed = function (acc, role) {
              var authority = $scope.authority_of(acc, role);
              if (authority) {
                  $scope.revoke_authority(authority);
              } else {
                  authority = { account: acc, role: role };
                  $scope.grant_authority(authority);
              }
          }
  
          // Them moi authority
          $scope.grant_authority = function (authority) {
              $http.post(`/rest/authorities`, authority).then(response => {
                  $scope.authorities.push(response.data);
                  alert("Cấp quyền sử dụng thành công");
              }).catch(error => {
                  alert("Cấp quyền thất bại");
                  console.log("Error", error);
              })
          }
  
          // Xoa authority
          $scope.revoke_authority = function (authority) {
              $http.delete(`/rest/authorities/${authority.id}`).then(response => {
                  var index = $scope.authorities.findIndex(a => a.id == authority.id);
                  $scope.authorities.splice(index, 1);
                  alert("Thu hồi quyền mời thành công");
              }).catch(error => {
                  alert("Thu hồi quyền sử dụng thất bại");
                  console.log("Error", error);
              })
          }
      };
  
      $scope.authInitialize();
	
})