#!/bin/sh
set -e

echo "[init] Check environment variables..."

if [ -n "$GOOGLE_CLIENT_ID" ] && [ -n "$GOOGLE_CLIENT_SECRET" ]; then

  echo "[init] Install the necessary packages..."
  apk add --no-cache gettext

  echo "[init] Substitute variables into test-realm.json..."
  envsubst </input/test-realm.json >/output/test-realm.json

  echo "[init] Done."
else
  echo "[init] Variables are not set. Remove unnecessary blocks..."
  apk add --no-cache jq

  cp /input/test-realm.json /output/test-realm.json

  tmp_file=$(mktemp)
  jq 'if .identityProviders then
        .identityProviders |= map(select(.alias != "google"))
      else
        .
      end' /output/test-realm.json >"$tmp_file"
  mv "$tmp_file" /output/test-realm.json

  echo "[init] Done."
fi
chmod 644 /output/test-realm.json
