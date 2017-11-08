export default class BaseService {
	static $inject = ['$http', 'ENV'];

	constructor($http, ENV) {
		this.$http = $http;
		this.ENV = ENV;
	}

	absUrlPath(url) {
		return this.ENV.BACKEND_BASE_URL + url;
	}

	get(url) {
		return this.$http.get(this.absUrlPath(url));
	}

	post(url, data) {
		return this.$http.post(this.absUrlPath(url), JSON.stringify(data));
	}

	delete(url) {
		return this.$http.delete(this.absUrlPath(url));
	}
}