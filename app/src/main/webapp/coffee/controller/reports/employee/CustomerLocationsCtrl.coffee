CustomerLocationsCtrl = ($scope, $http) ->

  $scope.customers = []
  $scope.defaultPos =
    lat: 8
    lng: 80

  geocoder = new google.maps.Geocoder()

  $scope.getPosition = (after, address) ->
    position = {}

    geocoder.geocode
      address: address
    , (results, status) ->
      if status is google.maps.GeocoderStatus.OK
        position = results[0].geometry.location
        after(position)
      else
        console.log("Geocode was not successful for the following reason: " + status)

    return position



  $scope.getContactDetails = (after,userId) ->

    contact = {}

    $http.get("/contactdetails?userId=" + userId).success((data) ->
      contact = data
      after(contact)
    ).error(() ->
      console.log("error in /contactdetails?userId=")
      contact
    )


  $scope.options =
    map:
      center: new google.maps.LatLng($scope.defaultPos.lat, $scope.defaultPos.lng)
      zoom: 6
      mapTypeId: google.maps.MapTypeId.ROADMAP

    notselected:
      icon: "https://maps.gstatic.com/mapfiles/ms2/micons/red-dot.png"

    selected:
      icon: "https://maps.gstatic.com/mapfiles/ms2/micons/yellow-dot.png"


  $http.get("/customer/all").success((data) ->

    stopVar = 0

    for customer in data
      stopVar++
      if stopVar > 15
        break

      $scope.getContactDetails((contactDetails)->

        console.log contactDetails
        address = contactDetails.addressLine1 + ", " + contactDetails.addressLine2 + ", "  + contactDetails.city + ", "  + contactDetails.province + ", "  + contactDetails.country

        $scope.getPosition((data)->
          console.log data
          $scope.customers.unshift(
            id: customer.userId
            name: contactDetails.name
            location:
              lat: data.nb
              lng: data.ob
          )
        , address
        )

      ,customer.userId)
  ).error(() ->
    console.log("error in /customer/all")
  )


  $scope.getCustomerOpts = (customer) ->
    angular.extend
      title: customer.name
    , (if customer.selected then $scope.options.selected else $scope.options.notselected)


  $scope.selectCustomer = (customer) ->
    $scope.customer.selected = false  if $scope.customer
    $scope.customer = customer
    $scope.customer.selected = true
    $scope.$broadcast "gmMarkersUpdate", "customer"