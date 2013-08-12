package framework.core.service.impl;

import java.util.List;

import javax.inject.Named;

import framework.core.entity.Client;
import framework.core.persistence.ClientDao;
import framework.core.service.ClientService;

@Named
public class ClientServiceImpl extends AbstractService<Client, ClientDao> implements ClientService {

	private static final long serialVersionUID = -8247412604665890406L;

	@Override
	public Client findClientByName(String name) {
		List<Client> clients = this.getPersistence().findClientByName(name);
		if (clients.size()>0) {
			return clients.get(0);
		}
		return null;
	}

}
