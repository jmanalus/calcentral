(function(angular) {

  'use strict';

  /**
   * We make sure to hide the location bar on mobile devices.
   * NOTE: This will only happen when the actual content is high enough.
   */
  angular.module('calcentral.services').factory('locationbarInterceptorService', ['$window' , function($window) {

    var success = function(response) {

      // We make sure to only scroll when the user hasn't scrolled before
      if (!pageYOffset) {
        $window.scrollTo(0, 1);
      }

      return response;
    };

    // Return the function
    return function(promise) {
      return promise.then(success);
    };

  }]);

}(window.angular));
