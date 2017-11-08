import BaseService from './baseService';

export default class AccountService extends BaseService {

	login(user) {
		return super.post('login', user);
	}
}